package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.HolidayMapper;
import com.ebrun.holiday.dao.VacationMapper;
import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.service.VacationService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/20.
 */
@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {
    //@Autowired
    private HolidayMapper holidayMapper;

    public HolidayMapper getHolidayMapper() {
        return holidayMapper;
    }

    @Autowired
    public void setHolidayMapper(HolidayMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    private VacationMapper vacationMapper;

    public VacationMapper getVacationMapper() {
        return vacationMapper;
    }

    @Autowired
    public void setVacationMapper(VacationMapper vacationMapper) {
        this.vacationMapper = vacationMapper;
    }

    private VacationService vacationService;

    public VacationService getVacationService() {
        return vacationService;
    }

    @Autowired
    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    /**
     * 这个复杂的算法，我居然想了一夜，java实现和javaScript各实现一次
     * 根据亿邦动力2015财年度，年假算法
     *
     * @param entryDate
     * @return
     */
    @Override
    public Integer calculateHolidaysByEntryDate(Date entryDate) {
        SimpleDateFormat sdfYMD = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        SimpleDateFormat sdfYYYY = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY);
        SimpleDateFormat sdfMM = new SimpleDateFormat((Constant.DATE_FORMAT_MM));
        SimpleDateFormat sdfDD = new SimpleDateFormat((Constant.DATE_FORMAT_DD));

        Integer holidays;//年假天数

        Date currentDate = new Date();//当前日期

        int currentYear = Integer.parseInt(sdfYYYY.format(currentDate));//当前年份
        int currentMonth = Integer.parseInt(sdfMM.format(currentDate));//当前月份
        int currentDay = Integer.parseInt(sdfDD.format(currentDate));//当前day
        int myYear = Integer.parseInt(sdfYYYY.format(entryDate));//入职年份
        int myMonth = Integer.parseInt(sdfMM.format(entryDate));//入职月份
        int myDay = Integer.parseInt(sdfDD.format(entryDate));//入职day
        String currentDateString = sdfYMD.format(currentDate);//当前日期字符串
        String myDateString = sdfYMD.format(entryDate);//入职日期字符串

        Integer fiscalYear;//当前财年
        Integer fiscalMonth = Constant.FISCAL_MONTH;//以4月来划分财年
        Integer fiscalCount;//财年个数

        if (currentMonth < fiscalMonth) {//如果现在还没到4月
            fiscalYear = currentYear - 1;//财年为当前年份-1
        } else {//如果现在已经到了或过了4月
            fiscalYear = currentYear;//财年为当前年份
        }
        if (myMonth < fiscalMonth) {//如果入职月份早于4月
            fiscalCount = fiscalYear - myYear + 2;//当前我正在经历第fiscalCount个财年
        } else {
            fiscalCount = fiscalYear - myYear + 1;
        }

        Integer monthCount;//入职月数
        if (fiscalCount > 1) {//如果正在经历n+1财年
            holidays = fiscalCount + 4 - 1;//休假天数
            if (holidays > 15) {//最高15天
                holidays = 15;
            }
        } else {//如果正在经历第1个财年（没有跨4月1日，即到明年4月1日也只算半个财年，记为第1个财年）
            if (currentMonth < fiscalMonth) {//说明这是第 N 个财年，但是现在的年份是 N+1 年，也就是还没到财年月4月，即1，2，3月
                monthCount = (currentYear - myYear) * 12 + 4 - myMonth;
            } else {
                monthCount = 12 + 4 - myMonth;//这个地方就是这么算，不能改
            }
            if (myDay > 1) {//如果不满一个月，就舍去
                monthCount = monthCount - 1;
            }
            if (monthCount <= 0) {
                holidays = 0;
            } else {
                holidays = (monthCount * 4) / 12;//2015年度基本法上规定,4*（工作月份/12）
                if ((monthCount * 4) % 12 >= 6) {//模拟四舍五入
                    holidays = holidays + 1;
                }
                //holidays = new BigDecimal(monthCount / (12 / 4)).setScale(0, BigDecimal.ROUND_HALF_UP);
            }
            //以下是js方法
            //holidays = parseInt(monthCount/(12/4));//取整
            //holidays = Math.ceil(monthCount/(12/4));//向上取整
            //holidays = Math.round(monthCount / (12 / 4));//四舍五入
            //holidays = Math.floor(monthCount/(12/4));//向下取整

        }
        return holidays;
    }

    @Override
    public Map<String, Integer> calculateHolidaysMap(Date entryDate, Date leaveDate) {
        Map<String, Integer> map = new HashMap();

        SimpleDateFormat sdfYMD = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        SimpleDateFormat sdfYYYY = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY);
        SimpleDateFormat sdfMM = new SimpleDateFormat((Constant.DATE_FORMAT_MM));
        SimpleDateFormat sdfDD = new SimpleDateFormat((Constant.DATE_FORMAT_DD));

        Integer holidays;//年假天数

        Date currentDate = new Date();//当前日期

        int currentYear = Integer.parseInt(sdfYYYY.format(currentDate));//当前年份
        int currentMonth = Integer.parseInt(sdfMM.format(currentDate));//当前月份
        int currentDay = Integer.parseInt(sdfDD.format(currentDate));//当前day
        int entryYear = Integer.parseInt(sdfYYYY.format(entryDate));//入职年份
        int entryMonth = Integer.parseInt(sdfMM.format(entryDate));//入职月份
        int entryDay = Integer.parseInt(sdfDD.format(entryDate));//入职day

        String currentDateString = sdfYMD.format(currentDate);//当前日期字符串
        String myDateString = sdfYMD.format(entryDate);//入职日期字符串

        Integer fiscalYear;//当前财年
        Integer fiscalMonth = Constant.FISCAL_MONTH;//以4月来划分财年
        Integer fiscalCount;//财年个数

        if (currentMonth < fiscalMonth) {//如果现在还没到4月
            fiscalYear = currentYear - 1;//财年为当前年份-1
        } else {//如果现在已经到了或过了4月
            fiscalYear = currentYear;//财年为当前年份
        }
        if (entryMonth < fiscalMonth) {//如果入职月份早于4月
            fiscalCount = fiscalYear - entryYear + 2;//当前我正在经历第fiscalCount个财年
        } else {
            fiscalCount = fiscalYear - entryYear + 1;
        }

        Integer monthCount;//入职月数
        if (fiscalCount > 1) {//如果正在经历n+1财年
            holidays = fiscalCount + 4 - 1;//休假天数
            if (holidays > 15) {//最高15天
                holidays = 15;
            }
        } else {//如果正在经历第1个财年（没有跨4月1日，即到明年4月1日也只算半个财年，记为第1个财年）
            if (currentMonth < fiscalMonth) {//说明这是第 N 个财年，但是现在的年份是 N+1 年，也就是还没到财年月4月，即1，2，3月
                monthCount = (currentYear - entryYear) * 12 + 4 - entryMonth;
            } else {
                monthCount = 12 + 4 - entryMonth;//这个地方就是这么算，不能改
            }
            if (entryDay > 1) {//如果不满一个月，就舍去
                monthCount = monthCount - 1;
            }
            if (monthCount <= 0) {
                holidays = 0;
            } else {
                holidays = (monthCount * 4) / 12;//2015年度基本法上规定,4*（工作月份/12）
                if ((monthCount * 4) % 12 >= 6) {//模拟四舍五入
                    holidays = holidays + 1;
                }
            }
        }

        int leaveCount = 0;
        if (leaveDate != null) {//引入离职日期，也就是当一个员工离职了，就不再对他离职后的年假每年都统计。
            int leaveYear = Integer.parseInt(sdfYYYY.format(leaveDate));//离职职年份
            int leaveMonth = Integer.parseInt(sdfMM.format(leaveDate));//离职月份
            int leaveDay = Integer.parseInt(sdfDD.format(leaveDate));//离职day
            if (leaveMonth < 4) {
                //fiscalCount = fiscalCount -(currentYear-leaveYear);
                leaveCount = (currentYear - leaveYear) + 1;
                fiscalCount = fiscalCount - leaveCount;
            } else {
                leaveCount = (currentYear - leaveYear);
                fiscalCount = fiscalCount - leaveCount;
            }
        }
        for (int i = 0; i < fiscalCount; i++) {
            String fiscalYearString = String.valueOf(fiscalYear - leaveCount - i);
            Integer holiday = holidays - leaveCount - i;
            map.put(fiscalYearString, holiday);
        }
        return map;
    }

    @Override
    public Map<String, Object> showHoliday(Integer selectEmployeeId, String fiscalYear) {
        List holidayInfo = holidayMapper.selectHolidayByFiscalYear(selectEmployeeId, fiscalYear);
        Map<String, Object> map = new HashMap<>();
        map.put("holidayInfo", holidayInfo.get(0));
        Integer fiscalYearIndex = calculateFiscalYearIndex(selectEmployeeId, fiscalYear);
        map.put("fiscalYearIndex",fiscalYearIndex);
        List vacationList = vacationService.selectVacationByFiscalYear(selectEmployeeId, fiscalYear);
        map.put("vacationList",vacationList);
        Double vacationCount = vacationService.selectVacationCountByFiscalYear(selectEmployeeId, fiscalYear);
        map.put("vacationCount",vacationCount);
        return map;
    }

    /**
     * 计算给定的财年号是员工的第几个财年
     * @param selectEmployeeId
     * @param fiscalYear
     * @return
     */
    public Integer calculateFiscalYearIndex(Integer selectEmployeeId, String fiscalYear){
        Integer fiscalYearIndex=0;
        Integer firstFiscalYear = holidayMapper.selectFirstFiscalYear(selectEmployeeId);
        fiscalYearIndex = Integer.parseInt(fiscalYear) - firstFiscalYear + 1;
        return fiscalYearIndex;
    };

    @Override
    public List getFiscalYearListByEmployeeId(Integer employeeId) {
        List fiscalYearList = holidayMapper.selectFiscalYearListByEmployee(employeeId);
        return fiscalYearList;
    }
}
