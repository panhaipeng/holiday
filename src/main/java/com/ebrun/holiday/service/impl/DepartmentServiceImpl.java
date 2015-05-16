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
}
