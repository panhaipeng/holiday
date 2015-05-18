package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /**
     * 根据邮箱获取用户，用来登录验证
     * @param email
     * @return
     */
    Employee selectByEmail(String email);

    /**
     * 分页查询employeeList
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List selectEmployeeByPage(@Param("pageNumber")Integer pageNumber, @Param("pageSize")Integer pageSize);

    Integer selectEmployeeCount();
}