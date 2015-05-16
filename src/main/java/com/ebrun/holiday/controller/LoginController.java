package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.LoginService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
    //@ResponseBody
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,Map<String, Employee> map) {
        Employee employee = loginService.login(email, password);
        if (employee != null) {
            map.put(Constant.EMPLOYEE_HTTP_SESSION_NAME,employee);
            return "index";
        }else{
            return "fail";
        }
    }
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpSession httpSession,SessionStatus sessionStatus){
        loginService.logout(httpSession);
        sessionStatus.setComplete();
        return "index";
    }
}
