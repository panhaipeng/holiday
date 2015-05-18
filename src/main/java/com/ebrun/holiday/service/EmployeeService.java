package com.ebrun.holiday.service;

import com.ebrun.holiday.model.Employee;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DaoDao on 2015/5/11.
 */

public interface EmployeeService {
    /**
     * 根据 id 获取员工对象
     * @param id
     * @return
     */
    public Employee getEmployeeById(Integer id);

    /**
     * 根据 HttpSession 获取员工id
     * @param httpSession
     * @return
     */
    public Integer getEmployeeIdByHttpSession(HttpSession httpSession);

    /**
     * 分页查询员工列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List getEmployeeListByPage(Integer pageNumber,Integer pageSize);

    public Integer getEmployeeListPageCount();
}
