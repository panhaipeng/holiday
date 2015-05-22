package com.ebrun.holiday.service.impl;

import com.ebrun.holiday.dao.DepartmentMapper;
import com.ebrun.holiday.dao.EmployeeMapper;
import com.ebrun.holiday.dao.HolidayMapper;
import com.ebrun.holiday.dao.VacationMapper;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.model.Holiday;
import com.ebrun.holiday.model.Vacation;
import com.ebrun.holiday.service.DepartmentService;
import com.ebrun.holiday.service.EmployeeService;
import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.service.VacationService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private VacationService vacationService;

    public VacationService getVacationService() {
        return vacationService;
    }

    @Autowired
    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    private DepartmentMapper departmentMapper;

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    @Autowired
    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    private VacationMapper vacationMapper;

    public VacationMapper getVacationMapper() {
        return vacationMapper;
    }

    @Autowired
    public void setVacationMapper(VacationMapper vacationMapper) {
        this.vacationMapper = vacationMapper;
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
        Date leaveDate = null;

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
            //Date leaveDate = null;
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

        if (entryDate != null) {
            Map<String, Integer> map = holidayService.calculateHolidaysMap(entryDate, leaveDate);
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

    @Override
    public void updateEmployee(Integer inputEmployeeId, String inputEmployeeNumber, String inputEmployeeName,
                               String inputEmployeeEmail, String inputEmployeePassword, String inputEmployeeEntryDate,
                               String inputEmployeeLeaveDate, Integer inputEmployeeDepartmentId,
                               Integer inputEmployeeIfAdministrationValue, String inputEmployeeRemark) {
        Employee employee = new Employee();
        Holiday holiday = new Holiday();
        Date entryDate = null;
        Date leaveDate = null;
        employee.setId(inputEmployeeId);

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
            //Date leaveDate = null;
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

        employeeMapper.updateByPrimaryKeySelective(employee);

        Integer employeeId = employee.getId();
        LOGGER.info("这个是修改员工返回的 int 值：" + employeeId);

        //接下来要做修改员工的过程中比较麻烦的一个操作：
        //1，修改员工的时候会把入职日期，离职日期给改掉，那么相对应的holiday表中的信息和vacation表中的信息也需要跟着修改。
        //2，首先删除这个员工在holiday表中的信息，更新员工信息的时候，再在holiday表中把新的数据一组一组insert into。
        //3，将该员工在vacation表中的，非在职期间的信息，即修改入职离职日期之后，在这段区间之外的数据变为了无效数据，清除之。

        holidayMapper.deleteByEmployeeId(employeeId);

        if (entryDate != null) {
            Map<String, Integer> map = holidayService.calculateHolidaysMap(entryDate, leaveDate);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String fiscalYear = entry.getKey();//财年
                Integer holidays = entry.getValue();//假期
                LOGGER.info("key= " + fiscalYear + " and value= " + holidays);

                holiday.setEmployeeId(employeeId);
                holiday.setFiscalYear(fiscalYear);
                holiday.setHolidays(holidays);
                holidayMapper.insertSelective(holiday);
                LOGGER.info("插入holiday表：" + employeeId + "," + fiscalYear + "," + holidays);
            }
        }

        //清除vacation表中多余的数据
        if (entryDate != null) {
            vacationService.cleanExcessByEmployeeIdAndDate(employeeId, entryDate, leaveDate);
        }
    }

    @Override
    public void deleteEmployee(Integer deleteEmployeeId) {

        //执行删除员工操作要执行4张表。
        //1，employee表。2，如果这个id是department表中的领导，则department中所有的这个id改为1，admin。
        //3，删除holiday表中所有有关这个id的信息。4，删除vacation表中的信息。即完全清除这个id在数据库中的痕迹
        
        //此处由于有主外键约束关系，所以应该注意删除顺序，先删外键关联表

        vacationMapper.deleteByEmployeeId(deleteEmployeeId);
        holidayMapper.deleteByEmployeeId(deleteEmployeeId);
        departmentMapper.updateByDeleteEmployeeId(deleteEmployeeId);
        employeeMapper.deleteByPrimaryKey(deleteEmployeeId);
        
    }

    @Override
    public List selectEmployeeByKeyword(String employeeKeyword) {
        List list=null;
        if (employeeKeyword!=null){
            list = employeeMapper.selectEmployeeByKeyword(employeeKeyword);
        }
        return list;
    }
}
