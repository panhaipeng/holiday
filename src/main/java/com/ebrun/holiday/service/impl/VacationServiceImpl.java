package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.VacationMapper;
import com.ebrun.holiday.service.VacationService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DaoDao on 2015/5/21.
 */
@Service("vacationService")
public class VacationServiceImpl implements VacationService{
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private VacationMapper vacationMapper;

    public VacationMapper getVacationMapper() {
        return vacationMapper;
    }
    @Autowired
    public void setVacationMapper(VacationMapper vacationMapper) {
        this.vacationMapper = vacationMapper;
    }

    @Override
    public void cleanExcessByEmployeeIdAndDate(Integer employeeId, Date entryDate, Date leaveDate) {
        if(employeeId==null){
            employeeId=0;
        }
        if(leaveDate ==null){
            leaveDate = new Date();
        }
        vacationMapper.deleteByEmployeeIdAndDate(employeeId, entryDate, leaveDate);
    }

    @Override
    public List selectVacationByFiscalYear(Integer selectEmployeeId, String fiscalYear) {
        String fiscalYearStartString = fiscalYear+ Constant.FISCAL_START;
        String fiscalYearEndString = (Integer.parseInt(fiscalYear)+1)+Constant.FISCAL_END;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        Date fiscalYearStart=null;
        Date fiscalYearEnd=null;
        try {
            fiscalYearStart = sdf.parse(fiscalYearStartString);
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("财年开始日期字符串转换Date错误：", e);
        }
        try {
            fiscalYearEnd = sdf.parse(fiscalYearEndString);
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("财年结束日期字符串转换Date错误：", e);
        }
        List vacationList = vacationMapper.selectVacationByFiscalYear(selectEmployeeId,fiscalYearStart,fiscalYearEnd);
        return vacationList;
    }

    @Override
    public Double selectVacationCountByFiscalYear(Integer selectEmployeeId, String fiscalYear) {
        Double vacationCount=0.0;
        Integer fullDay = 0;
        Integer halfDay = 0;
        String fiscalYearStartString = fiscalYear+ Constant.FISCAL_START;
        String fiscalYearEndString = (Integer.parseInt(fiscalYear)+1)+Constant.FISCAL_END;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        Date fiscalYearStart=null;
        Date fiscalYearEnd=null;
        try {
            fiscalYearStart = sdf.parse(fiscalYearStartString);
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("财年开始日期字符串转换Date错误：", e);
        }
        try {
            fiscalYearEnd = sdf.parse(fiscalYearEndString);
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("财年结束日期字符串转换Date错误：", e);
        }
        //fullDay = vacationMapper.selectVacationByFullDay(selectEmployeeId,fiscalYearStart,fiscalYearEnd);
        //halfDay = vacationMapper.selectVacationByHalfDay(selectEmployeeId, fiscalYearStart, fiscalYearEnd);
        if(halfDay!=0){
            vacationCount = fullDay + new Double(halfDay/2);
        }else{
            //vacationCount = (Double)(fullDay.intValue());
        }
        
        return vacationCount;
    }
}
