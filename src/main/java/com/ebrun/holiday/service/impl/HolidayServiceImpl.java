package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.util.Constant;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/20.
 */
@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {
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
            }else {
                monthCount = 12 + 4 - myMonth;//这个地方就是这么算，不能改
            }
            if (myDay>1) {//如果不满一个月，就舍去
                monthCount = monthCount - 1;
            }
            if(monthCount<=0){
                holidays=0;
            }else{
                holidays =(monthCount*4)/12;//2015年度基本法上规定,4*（工作月份/12）
                if((monthCount*4)%12>=6){//模拟四舍五入
                    holidays = holidays+1;
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
    public Map<String, Integer> calculateHolidaysMap(Date entryDate) {
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
            }else {
                monthCount = 12 + 4 - myMonth;//这个地方就是这么算，不能改
            }
            if (myDay>1) {//如果不满一个月，就舍去
                monthCount = monthCount - 1;
            }
            if(monthCount<=0){
                holidays=0;
            }else{
                holidays =(monthCount*4)/12;//2015年度基本法上规定,4*（工作月份/12）
                if((monthCount*4)%12>=6){//模拟四舍五入
                    holidays = holidays+1;
                }
            }
        }
        
        for(int i=0;i<fiscalCount;i++){
            String fiscalYearString = String.valueOf(fiscalYear-i);
            Integer holiday = holidays-i;
            map.put(fiscalYearString,holiday);
        }
        return map;
    }
}
