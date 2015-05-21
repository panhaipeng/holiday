package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Vacation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface VacationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vacation record);

    int insertSelective(Vacation record);

    Vacation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vacation record);

    int updateByPrimaryKey(Vacation record);
    
    /*以上是generator工具自动生成的*/

    /**
     * 删除在 entryDate 和 leaveDate 之间以外的，id 为 employeeId 的记录
     * @param employeeId
     * @param entryDate
     * @param leaveDate
     * @return
     */
    int deleteByEmployeeIdAndDate(@Param("employeeId") Integer employeeId,@Param("entryDate") Date entryDate, @Param("leaveDate")Date leaveDate);

    int deleteByEmployeeId(Integer deleteEmployeeId);
}