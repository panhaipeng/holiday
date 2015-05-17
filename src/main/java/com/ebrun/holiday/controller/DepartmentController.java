package com.ebrun.holiday.controller;

import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/15.
 */
@Controller
public class DepartmentController {
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
@Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private DepartmentService departmentService;

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/showDepartment", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showDepartment() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber(Constant.TOP_DEPARTMENT_NUMBER);
        return map;
    }
    @RequestMapping(value = "/showDepartmentByAdministration", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showDepartmentByAdministration(HttpSession httpSession) {
        String departmentNumber;
        Employee employee = (Employee) httpSession.getAttribute(Constant.EMPLOYEE_HTTP_SESSION_NAME);
        if(employee==null){
            departmentNumber = null;
        }else if(employee.getIfAdministration()){
            departmentNumber = Constant.TOP_DEPARTMENT_NUMBER;
        }else {
            Integer employeeId = employeeService.getEmployeeIdByHttpSession(httpSession);
            departmentNumber = departmentService.getDepartmentNumberByDepartmentLeader(employeeId);
        }
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber2(departmentNumber);
        return map;
    }
    @RequestMapping(value = "/insertDepartment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insertDepartment(@RequestParam("inputSuperiorDepartmentNumber") String inputSuperiorDepartmentNumber,
                                               @RequestParam("inputDepartmentName") String inputDepartmentName,
                                               @RequestParam("inputDepartmentLeader") Integer inputDepartmentLeader,
                                               @RequestParam("inputDepartmentRemark") String inputDepartmentRemark){
        departmentService.insertDepartment(inputSuperiorDepartmentNumber,inputDepartmentName,inputDepartmentLeader,inputDepartmentRemark);
        Map<String,String> map = new HashMap();
        map.put("insert","success");
        return map;
    }
    @RequestMapping(value = "/deleteDepartment",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> deleteDepartment(@RequestParam("deleteDepartmentId") Integer deleteDepartmentId){
        departmentService.deleteDepartment(deleteDepartmentId);
        Map<String, String> map = new HashMap();
        map.put("delete","success");
        return map;
    }
}
