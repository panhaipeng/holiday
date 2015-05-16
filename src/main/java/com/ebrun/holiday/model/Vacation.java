package com.ebrun.holiday.model;

import java.util.Date;

public class Vacation {
    private Integer id;

    private Integer employeeId;

    private Date vacationDate;

    private Boolean vacationStatus;

    private String remark;

    private Integer createPerson;

    private Date createTime;

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

    public Date getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(Date vacationDate) {
        this.vacationDate = vacationDate;
    }

    public Boolean getVacationStatus() {
        return vacationStatus;
    }

    public void setVacationStatus(Boolean vacationStatus) {
        this.vacationStatus = vacationStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}