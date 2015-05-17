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
    
    List<Department> selectDepartmentsBySuperDepartmentNumber(String superDepartmentNumber);

    Department selectDepartmentByDepartmentLeader(Integer departmentLeaderId);

    /**
     * 关联查询，查出领导id对应的领导姓名
     * @param superDepartmentNumber
     * @return
     */
    List selectDepartmentsBySuperDepartmentNumber1(String superDepartmentNumber);

    List<String> selectDepartmentNumbersBySuperiorDepartmentNumber(String inputSuperiorDepartmentNumber);
}