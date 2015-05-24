package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    /*以上是generator工具自动生成的*/

    List<Department> selectDepartmentsBySuperDepartmentNumber(String superDepartmentNumber);

    Department selectDepartmentByDepartmentLeader(Integer departmentLeaderId);

    /**
     * 根据上级部门号获取部门列表
     *
     * @param superDepartmentNumber
     * @return
     */
    List selectDepartmentsBySuperDepartmentNumber1(String superDepartmentNumber);

    List<String> selectDepartmentNumbersBySuperiorDepartmentNumber(String inputSuperiorDepartmentNumber);

    int updateByDeleteEmployeeId(Integer deleteEmployeeId);

}