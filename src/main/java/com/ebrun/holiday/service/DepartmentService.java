package com.ebrun.holiday.service;

import com.ebrun.holiday.model.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/14.
 */
public interface DepartmentService {
    /**
     * 返回下一级下属部门
     * @param superDepartmentNumber 部门号的开头
     * @return 部门的一个list对象
     */
    public Map<String,Department> selectDepartmentsBySuperDepartmentNumber(String superDepartmentNumber);

    /**
     * 返回所有下属部门
     * @param superDepartmentNumber
     * @return
     */
    public Map<String,Object> selectSubordinateDepartmentsBySuperDepartmentNumber(String superDepartmentNumber);

    /**
     * 根据部门领导id获取部门号
     * @param departmentLeaderId 
     * @return
     */
    public String getDepartmentNumberByDepartmentLeader(Integer departmentLeaderId);
}
