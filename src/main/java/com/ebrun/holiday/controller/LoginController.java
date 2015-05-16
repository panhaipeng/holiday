package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Employee;
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
@SessionAttributes("employee")
public class LoginController {
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestParam("email") String email, @RequestParam("password") String password,Map<String, Employee> map) {
        Employee employee = loginService.login(email, password);
        Map<String,String> loginMap = new HashMap<>();
        if (employee != null) {
            map.put(Constant.EMPLOYEE_HTTP_SESSION_NAME,employee);
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
