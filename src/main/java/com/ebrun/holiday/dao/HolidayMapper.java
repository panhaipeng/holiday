package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Holiday;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HolidayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Holiday record);

    int insertSelective(Holiday record);

    Holiday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
    
    /*以上是generator工具自动生成的*/

    /**
     * 当删除员工的时候，把holiday中关于他的信息删掉
     * @param employeeId
     * @return
     */
    int deleteByEmployeeId(Integer employeeId);
    
    List selectHolidayByFiscalYear(@Param("selectEmployeeId")Integer selectEmployeeId, @Param("fiscalYear")String fiscalYear);
}