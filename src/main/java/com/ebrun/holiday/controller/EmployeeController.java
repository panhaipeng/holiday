package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/main", method=RequestMethod.GET)
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/showEmployeeListByPage",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> showEmployeeListByPage(@RequestParam("pageNumber") Integer pageNumber,@RequestParam("pageSize")Integer pageSize){
        Map<String,Object>map =new HashMap();
        Integer pageCount = employeeService.getEmployeeListPageCount();
        List employeeList = employeeService.getEmployeeListByPage(pageNumber,pageSize);
        map.put("employeeList",employeeList);
        map.put("pageNumber",pageNumber);
        map.put("pageCount",pageCount);
        return map;
    }
}
