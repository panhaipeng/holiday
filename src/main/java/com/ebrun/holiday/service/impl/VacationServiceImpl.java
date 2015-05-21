package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.VacationMapper;
import com.ebrun.holiday.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by DaoDao on 2015/5/21.
 */
@Service("vacationService")
public class VacationServiceImpl implements VacationService{
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
}
