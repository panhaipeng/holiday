package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Department;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.LoginService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by DaoDao on 2015/5/13.
 */
@Controller
//@SessionAttributes(value = "employee")
public class LoginController {
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
private DepartmentService departmentService;

    public DepartmentService getDepartmentService() {
        return departmentService;
    }
@Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession httpSession) {
        Employee employee = loginService.login(email, password);
        Map<String,String> loginMap = new HashMap<>();
        if (employee != null) {
            //map.put(Constant.EMPLOYEE_HTTP_SESSION_NAME,employee);
            httpSession.setAttribute(Constant.EMPLOYEE_HTTP_SESSION_NAME, employee);
            Department department = departmentService.getDepartmentByDepartmentLeaderId(employee.getId());
            if(department!=null){
                //map.put(Constant.DEPARTMENT_LEADER_HTTP_SESSION_NAME,department);
                httpSession.setAttribute(Constant.DEPARTMENT_LEADER_HTTP_SESSION_NAME, department);
            }else{
                httpSession.setAttribute(Constant.DEPARTMENT_LEADER_HTTP_SESSION_NAME, null);
            }
            loginMap.put("login","success");
            return loginMap;
        }else{
            loginMap.put("login","fail");
            return loginMap;
        }
    }
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> logout(HttpSession httpSession,SessionStatus sessionStatus){
        loginService.logout(httpSession);
        sessionStatus.setComplete();
        return null;
    }
}
