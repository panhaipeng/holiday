package com.ebrun.holiday.model;

import java.math.BigDecimal;

public class Holiday {
    private Integer id;

    private Integer employeeId;

    private String fiscalYear;

    private Integer holidays;

    private BigDecimal vacation;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear == null ? null : fiscalYear.trim();
    }

    public Integer getHolidays() {
        return holidays;
    }

    public void setHolidays(Integer holidays) {
        this.holidays = holidays;
    }

    public BigDecimal getVacation() {
        return vacation;
    }

    public void setVacation(BigDecimal vacation) {
        this.vacation = vacation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}