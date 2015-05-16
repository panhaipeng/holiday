package spring;

import com.alibaba.fastjson.JSON;
import com.ebrun.holiday.model.Department;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by DaoDao on 2015/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class TestMyBatis {
    private static final Logger LOGGER = Logger.getLogger(TestMyBatis.class);
    private DepartmentService departmentService;

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    public void test1() {
        Employee employee = employeeService.getEmployeeById(1);
        LOGGER.info(JSON.toJSONStringWithDateFormat(employee, Constant.DATE_FORMAT_YMDHMS));
    }

    @Test
    public void test2() {
        Map<String, Department> map = departmentService.selectDepartmentsBySuperDepartmentNumber(Constant.TOP_DEPARTMENT_NUMBER);
        LOGGER.info(JSON.toJSONString(map));
    }
    @Test
    public void test3() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber(Constant.TOP_DEPARTMENT_NUMBER);
        LOGGER.info(JSON.toJSONString(map));
    }
    @Test
    public void test4(){
        String departmentNumber = departmentService.getDepartmentNumberByDepartmentLeader(3);
        LOGGER.info(JSON.toJSONString(departmentNumber));
    }
    @Test
    public void test5() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber(null);
        LOGGER.info(JSON.toJSONString(map));
    }
    @Test
    public void test6() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber1(Constant.TOP_DEPARTMENT_NUMBER);
        LOGGER.info(JSON.toJSONString(map));
    }
    @Test
    public void test7() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber2(Constant.TOP_DEPARTMENT_NUMBER);
        LOGGER.info(JSON.toJSONString(map));
    }
}
