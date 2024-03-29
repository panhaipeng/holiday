package com.ebrun.holiday.service;

import com.ebrun.holiday.model.Employee;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DaoDao on 2015/5/11.
 */

public interface EmployeeService {
    /**
     * 根据 id 获取员工对象
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Integer id);

    /**
     * 根据 HttpSession 获取员工id
     *
     * @param httpSession
     * @return
     */
    public Integer getEmployeeIdByHttpSession(HttpSession httpSession);

    /**
     * 分页查询员工列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List getEmployeeListByPage(String employeeKeyword, Integer pageNumber, Integer pageSize);

    /**
     * 获取总页数
     *
     * @param employeeKeyword
     * @return
     */
    public Integer getEmployeeListPageCount(String employeeKeyword);

    /**
     * 添加一条员工信息
     * @param inputEmployeeNumber
     * @param inputEmployeeName
     * @param inputEmployeeEmail
     * @param inputEmployeePassword
     * @param inputEmployeeEntryDate
     * @param inputEmployeeLeaveDate
     * @param inputEmployeeDepartmentId
     * @param inputEmployeeIfAdministrationValue
     * @param inputEmployeeRemark
     */
    public void insertEmployee(String inputEmployeeNumber, 
                               String inputEmployeeName,
                               String inputEmployeeEmail,
                               String inputEmployeePassword,
                               String inputEmployeeEntryDate,
                               String inputEmployeeLeaveDate,
                               Integer inputEmployeeDepartmentId,
                               Integer inputEmployeeIfAdministrationValue,
                               String inputEmployeeRemark);

    /**
     * 修改一条员工记录
     * @param inputEmployeeId
     * @param inputEmployeeNumber
     * @param inputEmployeeName
     * @param inputEmployeeEmail
     * @param inputEmployeePassword
     * @param inputEmployeeEntryDate
     * @param inputEmployeeLeaveDate
     * @param inputEmployeeDepartmentId
     * @param inputEmployeeIfAdministrationValue
     * @param inputEmployeeRemark
     */
    public void updateEmployee(Integer inputEmployeeId,
                               String inputEmployeeNumber,
                               String inputEmployeeName,
                               String inputEmployeeEmail,
                               String inputEmployeePassword,
                               String inputEmployeeEntryDate,
                               String inputEmployeeLeaveDate,
                               Integer inputEmployeeDepartmentId,
                               Integer inputEmployeeIfAdministrationValue,
                               String inputEmployeeRemark);

    /**
     * 根据员工ID删除一个员工
     * @param deleteEmployeeId
     */
    public void deleteEmployee(Integer deleteEmployeeId);

    /**
     * 根据关键词查询出员工列表，用于给部门指定领导和查出休假管理的员工
     * @param employeeKeyword
     * @return
     */
    public List selectEmployeeByKeyword(String employeeKeyword);

    public List selectEmployeeByKeywordAndDepartment(String employeeKeyword, String departmentNumber);
}
