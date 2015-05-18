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
     * 根据部门领导id获取部门号
     * @param departmentLeaderId 
     * @return
     */
    public String getDepartmentNumberByDepartmentLeader(Integer departmentLeaderId);

    public Map<String,Object> selectSubordinateDepartmentsBySuperDepartmentNumber2(String superDepartmentNumber);

    /**
     * 添加一个部门
     * @param inputSuperiorDepartmentNumber
     * @param inputDepartmentName
     * @param inputDepartmentLeader
     * @param inputDepartmentRemark
     */
    public void insertDepartment(String inputSuperiorDepartmentNumber,String inputDepartmentName,Integer inputDepartmentLeader,String inputDepartmentRemark);

    /**
     * 根据上级部门号获取所有同级部门号
     * @param inputSuperiorDepartmentNumber
     * @return
     */
    public List<String> getDepartmentNumbersBySuperiorDepartmentNumber(String inputSuperiorDepartmentNumber);

    /**
     * 根据部门 id 删除部门
     * @param deleteDepartmentId
     */
    public void deleteDepartment(Integer deleteDepartmentId);
    
    public void updateDepartment(Integer inputDepartmentId,String inputDepartmentName,Integer inputDepartmentLeader,String inputDepartmentRemark);
}
