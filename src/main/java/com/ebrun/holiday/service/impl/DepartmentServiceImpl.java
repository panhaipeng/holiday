package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.DepartmentMapper;
import com.ebrun.holiday.model.Department;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by DaoDao on 2015/5/14.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    //@Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }
    
    @Autowired
    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Map<String, Department> selectDepartmentsBySuperDepartmentNumber(String superDepartmentNumber) {
        List<Department> departmentsList = departmentMapper.selectDepartmentsBySuperDepartmentNumber(superDepartmentNumber);

        Map<String, Department> map = new HashMap();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.size(); i++) {
                Department department = departmentsList.get(i);
                String departmentNumber = department.getDepartmentNumber();
                while (departmentNumber.length() == superDepartmentNumber.length() + 2) {
                    map.put(departmentNumber, department);
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 这个算法我憋了三小时才写出来，我觉得过几天我也看不懂了
     * 应该会有更成熟的解决方案
     *
     * @param superDepartmentNumber
     * @return 
     */
    @Override
    public Map<String, Object> selectSubordinateDepartmentsBySuperDepartmentNumber(String superDepartmentNumber) {
        if(superDepartmentNumber==null){
            return null;
        }
        List<Department> departmentsList = departmentMapper.selectDepartmentsBySuperDepartmentNumber(superDepartmentNumber);
        Map<String, Object> map = new HashMap();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.size(); i++) {
                Department department = departmentsList.get(i);
                String departmentNumber = department.getDepartmentNumber();
                while (departmentNumber.length() == superDepartmentNumber.length() + 2) {
                    map.put(departmentNumber, department);
                    Map<String, Object> childMap = selectSubordinateDepartmentsBySuperDepartmentNumber(departmentNumber);
                    if (childMap != null && childMap.size() > 0) {
                        map.put(departmentNumber + Constant.SUBORDINATE_DEPARTMENT_FLAG, childMap);
                    }
                    break;
                }
            }
        }
        return map;
    }

    @Override
    public String getDepartmentNumberByDepartmentLeader(Integer departmentLeaderId) {
        if(departmentLeaderId==null){
            return null;
        }
        Department department = departmentMapper.selectDepartmentByDepartmentLeader(departmentLeaderId);
        String departmentNumber = department.getDepartmentNumber();
        return departmentNumber;
    }

    @Override
    public Map<String, Object> selectSubordinateDepartmentsBySuperDepartmentNumber1(String superDepartmentNumber) {
        if(superDepartmentNumber==null){
            return null;
        }
        List<Department> departmentsList = departmentMapper.selectDepartmentsBySuperDepartmentNumber(superDepartmentNumber);
        Map<String, Object> map = new HashMap();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.size(); i++) {
                Department department = departmentsList.get(i);
                String departmentNumber = department.getDepartmentNumber();
                while (departmentNumber.length() == superDepartmentNumber.length() + 2) {
                    Map<String, Object> map1 = new HashMap();
                    //map1.put(departmentNumber+"department",department);
                    map1.put("id",department.getId());
                    map1.put("departmentNumber",department.getDepartmentNumber());
                    map1.put("departmentName",department.getDepartmentName());
                    map1.put("departmentLeader",department.getDepartmentLeader());
                    map1.put("remark",department.getRemark());
                    Map<String, Object> childMap = selectSubordinateDepartmentsBySuperDepartmentNumber1(departmentNumber);
                    if (childMap != null && childMap.size() > 0) {
                        map1.put(Constant.SUBORDINATE_DEPARTMENT_FLAG, childMap);
                    }
                    map.put(departmentNumber, map1);
                    break;
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> selectSubordinateDepartmentsBySuperDepartmentNumber2(String superDepartmentNumber) {
        if(superDepartmentNumber==null){
            return null;
        }
        List departmentsList = departmentMapper.selectDepartmentsBySuperDepartmentNumber1(superDepartmentNumber);
        Map<String, Object> map = new HashMap();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.size(); i++) {
                Map<String,Object> department = (Map)departmentsList.get(i);
                String departmentNumber = (String) department.get("department_number");
                while (departmentNumber.length() == superDepartmentNumber.length() + 2) {
                    Map<String, Object> map1 = new HashMap();
                    //map1.put(departmentNumber+"department",department);
                    map1.put("id",department.get("id"));
                    map1.put("departmentNumber",department.get("department_number"));
                    map1.put("departmentName",department.get("department_name"));
                    map1.put("departmentLeader",department.get("department_leader"));
                    map1.put("remark",department.get("remark"));
                    map1.put("departmentLeaderName",department.get("department_leader_name"));
                    Map<String, Object> childMap = selectSubordinateDepartmentsBySuperDepartmentNumber2(departmentNumber);
                    if (childMap != null && childMap.size() > 0) {
                        map1.put(Constant.SUBORDINATE_DEPARTMENT_FLAG, childMap);
                    }
                    map.put(departmentNumber, map1);
                    break;
                }
            }
        }
        return map;
    }

    @Override
    public void insertDepartment(String inputSuperiorDepartmentNumber, String inputDepartmentName, Integer inputDepartmentLeader, String inputDepartmentRemark) {
        String inputDepartmentNumber=null;
        if(inputSuperiorDepartmentNumber!=null){
            List<String> numbersList = getDepartmentNumbersBySuperiorDepartmentNumber(inputSuperiorDepartmentNumber);
            for(int i=1;i<99;i++){
                if(i<10) {
                    inputDepartmentNumber = inputSuperiorDepartmentNumber+"0"+i;
                }else{
                    inputDepartmentNumber = inputSuperiorDepartmentNumber +i;
                }
                if(!numbersList.contains(inputDepartmentNumber)){
                    break;
                }else {
                    continue;
                }
            }
        }
        Department department = new Department();
        department.setDepartmentNumber(inputDepartmentNumber);
        department.setDepartmentName(inputDepartmentName);
        if(inputDepartmentLeader==null){
            inputDepartmentLeader=1;
        }
        department.setDepartmentLeader(inputDepartmentLeader);
        department.setRemark(inputDepartmentRemark);
        departmentMapper.insert(department);
    }

    @Override
    public List<String> getDepartmentNumbersBySuperiorDepartmentNumber(String inputSuperiorDepartmentNumber) {
        List<String> list = departmentMapper.selectDepartmentNumbersBySuperiorDepartmentNumber(inputSuperiorDepartmentNumber);
        return list;
    }

    @Override
    public void deleteDepartment(Integer deleteDepartmentId) {
        departmentMapper.deleteByPrimaryKey(deleteDepartmentId);
    }

    @Override
    public void updateDepartment(Integer inputDepartmentId, String inputDepartmentName, Integer inputDepartmentLeader, String inputDepartmentRemark) {
        Department department = new Department();
        department.setId(inputDepartmentId);
        department.setDepartmentName(inputDepartmentName);
        if(inputDepartmentLeader==null){
            inputDepartmentLeader=1;
        }
        department.setDepartmentLeader(inputDepartmentLeader);
        department.setRemark(inputDepartmentRemark);
        departmentMapper.updateByPrimaryKeySelective(department);
    }
}
