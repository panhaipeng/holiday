package spring;

import com.alibaba.fastjson.JSON;
import com.ebrun.holiday.dao.EmployeeMapper;
import com.ebrun.holiday.model.Department;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.service.VacationService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    private HolidayService holidayService;

    public HolidayService getHolidayService() {
        return holidayService;
    }

    @Autowired
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    private VacationService vacationService;

    public VacationService getVacationService() {
        return vacationService;
    }

    @Autowired
    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
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
    public void test4() {
        String departmentNumber = departmentService.getDepartmentNumberByDepartmentLeader(3);
        LOGGER.info(JSON.toJSONString(departmentNumber));
    }

    @Test
    public void test7() {
        Map<String, Object> map = departmentService.selectSubordinateDepartmentsBySuperDepartmentNumber(Constant.TOP_DEPARTMENT_NUMBER);
        LOGGER.info(JSON.toJSONString(map));
    }

    @Test
    public void test8() {
        List<String> list = departmentService.getDepartmentNumbersBySuperiorDepartmentNumber("eb00");
        LOGGER.info(list.toString());
    }

    @Test
    public void test9() {
        departmentService.insertDepartment("eb00", "部门3", 1, null);
    }

    @Test
    public void test10() {
        departmentService.deleteDepartment(22);
    }

    @Test
    public void test11() {
        departmentService.updateDepartment(16, "视觉1", null, "aaa");
    }

    @Test
    public void test12() {
        List list = employeeService.getEmployeeListByPage("2013", 1, 10);
        LOGGER.info(JSON.toJSONStringWithDateFormat(list, Constant.DATE_FORMAT_YMDHMS));
    }

    @Test
    public void test13() {
        Integer pageCount = employeeService.getEmployeeListPageCount("20131");
        LOGGER.info(pageCount);
    }

    @Test
    public void test14() {
        employeeService.insertEmployee("104", "A04", "A04@ebrun.com", "123", "2014-04-04", null, 16, 0, "我是来打酱油的！");
    }

    @Test
    public void test15() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        Date date = null;
        try {
            date = sdf.parse("2014-12-12");
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("日期字符串转换Date错误：", e);
        }
        Integer holidays = holidayService.calculateHolidaysByEntryDate(date);
        LOGGER.info(holidays);
    }

    @Test
    public void test16() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
        Date entryDate = null;
        try {
            entryDate = sdf.parse("2009-04-01");
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("日期字符串转换Date错误：", e);
        }
        Date leaveDate = null;
        try {
            leaveDate = sdf.parse("2012-12-12");
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("日期字符串转换Date错误：", e);
        }
        Map<String, Integer> map = holidayService.calculateHolidaysMap(entryDate, leaveDate);
        LOGGER.info(JSON.toJSONString(map));
    }
    
    @Test
    public void test17(){
        vacationService.cleanExcessByEmployeeIdAndDate(1, new Date(), null);
    }

    @Test
    public void test18() {
        employeeService.updateEmployee(26, "114", "A14", "A14@ebrun.com", "123", "2010-04-04", "2013-06-04", 16, 0, "我是来吃酱油的！");
    }
    
    @Test
    public void test19(){
        employeeService.deleteEmployee(26);
    }
    @Test
    public void test20(){
        List list = employeeService.selectEmployeeByKeyword("员工");
        LOGGER.info(JSON.toJSONString(list));
    }
    @Test
    public void test21(){
        Map<String,Object>map = holidayService.showHoliday(20,"2015");
        LOGGER.info(JSON.toJSONString(map));
    }
    @Test
    public void test22(){
        List list = vacationService.selectVacationByFiscalYear(20,"2015");
        LOGGER.info(JSON.toJSONStringWithDateFormat(list,Constant.DATE_FORMAT_YMDHMS));
    }
}
