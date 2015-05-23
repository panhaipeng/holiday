package com.ebrun.holiday.service;

import java.util.Date;
import java.util.List;

/**
 * Created by DaoDao on 2015/5/21.
 */
public interface VacationService {
    /**
     * 清除在修改了员工入职离职日期之后，无效的数据，即日期不再在职期间的记录
     *
     * @param employeeId
     * @param entryDate
     * @param leaveDate
     */
    public void cleanExcessByEmployeeIdAndDate(Integer employeeId, Date entryDate, Date leaveDate);

    /**
     * 根据财年号和员工ID获取休假列表
     *
     * @param selectEmployeeId
     * @param fiscalYear
     * @return
     */
    public List selectVacationByFiscalYear(Integer selectEmployeeId, String fiscalYear);

    /**
     * 根据财年号和员工ID计算当前财年已经休假的天数
     *
     * @param selectEmployeeId
     * @param fiscalYear
     * @return
     */
    public double selectVacationCountByFiscalYear(Integer selectEmployeeId, String fiscalYear);

    /**
     * 插入一条vacation数据
     * @param employeeId
     * @param vacationDate
     * @param vacationStatus
     */
    public void insertVacation(Integer employeeId, String vacationDate, Integer vacationStatus);

    public void deleteVacationById(Integer vacationId);

}
