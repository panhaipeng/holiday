package com.ebrun.holiday.service;

import java.util.Date;

/**
 * Created by DaoDao on 2015/5/21.
 */
public interface VacationService {
    public void cleanExcessByEmployeeIdAndDate(Integer employeeId, Date entryDate, Date leaveDate);
}
