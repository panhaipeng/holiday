package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.EmployeeMapper;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by DaoDao on 2015/5/12.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    public EmployeeMapper getEmployeeMapper() {
        return employeeMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer getEmployeeIdByHttpSession(HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute(Constant.EMPLOYEE_HTTP_SESSION_NAME);
        if(employee==null){
            return null;
        }
        Integer employeeId = employee.getId();
        return employeeId;
    }
}
