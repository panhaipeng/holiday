package com.ebrun.holiday.service;

import com.ebrun.holiday.model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DaoDao on 2015/5/13.
 */
public interface LoginService {
    public Employee login(String email,String password);
    public void logout(HttpSession httpSession);
}
