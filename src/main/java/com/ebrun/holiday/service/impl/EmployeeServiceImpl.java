package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.EmployeeMapper;
import com.ebrun.holiday.dao.HolidayMapper;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.model.Holiday;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/12.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeMapper getEmployeeMapper() {
        return employeeMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    private HolidayMapper holidayMapper;

    public HolidayMapper getHolidayMapper() {
        return holidayMapper;
    }

    @Autowired
    public void setHolidayMapper(HolidayMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    private DepartmentService departmentService;

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private HolidayService holidayService;

    public HolidayService getHolidayService() {
        return holidayService;
    }

    @Autowired
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer getEmployeeIdByHttpSession(HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute(Constant.EMPLOYEE_HTTP_SESSION_NAME);
        if (employee == null) {
            return null;
        }
        Integer employeeId = employee.getId();
        return employeeId;
    }

    @Override
    public List getEmployeeListByPage(String employeeKeyword, Integer pageNumber, Integer pageSize) {
        pageSize = Constant.DEFAULT_PAGE_SIZE;
        Integer index = (pageNumber - 1) * 10;
        List employeeList = employeeMapper.selectEmployeeByPage(employeeKeyword, index, pageSize);
        return employeeList;
    }

    @Override
    public Integer getEmployeeListPageCount(String employeeKeyword) {
        Integer integer = employeeMapper.selectEmployeeCount(employeeKeyword);
        Integer pageCount;
        if (integer == 0) {
            pageCount = 1;
        } else if (integer % 10 == 0) {
            pageCount = integer / 10;
        } else {
            pageCount = integer / 10 + 1;
        }
        return pageCount;
    }

    @Override
    public void insertEmployee(String inputEmployeeNumber, String inputEmployeeName, String inputEmployeeEmail,
                               String inputEmployeePassword, String inputEmployeeEntryDate, String inputEmployeeLeaveDate,
                               Integer inputEmployeeDepartmentId, Integer inputEmployeeIfAdministrationValue, String inputEmployeeRemark) {

        Employee employee = new Employee();
        Holiday holiday = new Holiday();
        Date entryDate = null;

        if (inputEmployeeNumber != null && inputEmployeeNumber.length() > 0) {
            employee.setEmployeeNumber(inputEmployeeNumber);
        }
        if (inputEmployeeName != null && inputEmployeeName.length() > 0) {
            employee.setName(inputEmployeeName);
        }
        if (inputEmployeeEmail != null && inputEmployeeEmail.length() > 0) {
            employee.setEmail(inputEmployeeEmail);
        }
        if (inputEmployeePassword != null && inputEmployeePassword.length() > 0) {
            employee.setPassword(inputEmployeePassword);
        }
        if (inputEmployeeEntryDate != null && inputEmployeeEntryDate.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
            //Date entryDate= null;
            try {
                entryDate = sdf.parse(inputEmployeeEntryDate);
            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("入职日期字符串转换Date错误：", e);
            }
            employee.setEntryDate(entryDate);
        }
        if (inputEmployeeLeaveDate != null && inputEmployeeLeaveDate.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YMD);
            Date leaveDate = null;
            try {
                leaveDate = sdf.parse(inputEmployeeLeaveDate);
            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("离职日期字符串转换Date错误：", e);
            }
            employee.setLeaveDate(leaveDate);
        }
        if (inputEmployeeDepartmentId == null) {
            inputEmployeeDepartmentId = 1;
            employee.setDepartmentId(inputEmployeeDepartmentId);
        } else if (inputEmployeeDepartmentId == 0) {
            inputEmployeeDepartmentId = 1;
            employee.setDepartmentId(inputEmployeeDepartmentId);
        } else {
            employee.setDepartmentId(inputEmployeeDepartmentId);
        }
        if (inputEmployeeIfAdministrationValue == null || inputEmployeeIfAdministrationValue == 0) {
            Boolean ifAdministration = false;
            employee.setIfAdministration(ifAdministration);
        } else if (inputEmployeeIfAdministrationValue == 1) {
            Boolean ifAdministration = true;
            employee.setIfAdministration(ifAdministration);
        }
        if (inputEmployeeRemark != null) {
            employee.setRemark(inputEmployeeRemark);
        }

        employeeMapper.insertSelective(employee);

        int employeeId = employee.getId();
        LOGGER.info("这个是添加员工返回的 int 值：" + employeeId);
        
        if(entryDate!=null) {
            Map<String, Integer> map = holidayService.calculateHolidaysMap(entryDate);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String fiscalYear = entry.getKey();
                Integer holidays = entry.getValue();
                LOGGER.info("key= " + fiscalYear + " and value= " + holidays);
                holiday.setEmployeeId(employeeId);
                holiday.setFiscalYear(fiscalYear);
                holiday.setHolidays(holidays);
                holidayMapper.insertSelective(holiday);
                LOGGER.info("插入holiday表：" + employeeId + "," + fiscalYear + "," + holidays);
            }
        }
    }
}
