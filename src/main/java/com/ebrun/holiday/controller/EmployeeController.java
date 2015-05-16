package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created by DaoDao on 2015/5/12.
 */
@Controller
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/showEmployee/{id}", method = RequestMethod.GET)
    public String showEmployee(@PathVariable("id") Integer id, Map<String, Object> map) {
        Employee employee = employeeService.getEmployeeById(id);
        map.put("employee", employee);
        return "employee";
    }
    @RequestMapping(value = "/showEmployee/{id}", method = RequestMethod.GET,params = "json")
    @ResponseBody
    public Employee showEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }
    @RequestMapping(value = "/index", method=RequestMethod.GET)
    public String index(){
        return "index";
    }
}
