package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.EmployeeMapper;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.LoginService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DaoDao on 2015/5/13.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private EmployeeMapper employeeMapper;

    public EmployeeMapper getEmployeeMapper() {
        return employeeMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee login(String email, String password) {
        if (email != null && password != null) {
            Employee employee = employeeMapper.selectByEmail(email);
            if (employee != null) {
                if (employee.getPassword().equals(password)) {
                    return employee;
                }
            }
        }
        return null;
    }

    @Override
    public void logout(HttpSession httpSession) {
        //httpSession.removeAttribute("employee");
        httpSession.setAttribute(Constant.EMPLOYEE_HTTP_SESSION_NAME,null);
        httpSession.setAttribute(Constant.DEPARTMENT_LEADER_HTTP_SESSION_NAME, null);
    }
}
