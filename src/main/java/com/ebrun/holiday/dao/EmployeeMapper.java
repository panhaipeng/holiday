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

    /*以上是generator工具自动生成的*/
    /**
     * 根据邮箱获取用户，用来登录验证
     * @param email
     * @return
     */
    Employee selectByEmail(String email);

    /**
     * 分页查询employeeList
     * @param index
     * @param pageSize
     * @return
     */
    List selectEmployeeByPage(@Param("employeeKeyword")String employeeKeyword,@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    /**
     * 根据关键词查询统计页数
     * @param employeeKeyword
     * @return
     */
    Integer selectEmployeeCount(@Param("employeeKeyword")String employeeKeyword);

    /**
     * 根据关键词查询出符合条件的员工列表，只要查出来姓名邮箱ID
     * @param employeeKeyword
     * @return
     */
    List selectEmployeeByKeyword(@Param("employeeKeyword")String employeeKeyword);

    /**
     * 根据关键词和部门查询员工
     * @param employeeKeyword
     * @param departmentNumber
     * @return
     */
    List selectEmployeeByKeywordAndDepartment(@Param("employeeKeyword")String employeeKeyword,@Param("departmentNumber")String departmentNumber);

    /**
     * 删除部门时，将该部门下的员工自动移到default下
     * @param deleteDepartmentId
     * @param defaultDepartmentId
     * @return
     */
    int updateToDefaultDepartmentId(@Param("deleteDepartmentId")Integer deleteDepartmentId,@Param("defaultDepartmentId")Integer defaultDepartmentId);
}