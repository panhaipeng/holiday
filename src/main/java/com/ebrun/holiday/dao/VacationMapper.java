package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Vacation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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

    /**
     * 删除员工时，把vacation表中相关的信息清除
     * @param deleteEmployeeId
     * @return
     */
    int deleteByEmployeeId(Integer deleteEmployeeId);

    /**
     * 根据财年号和员工id查询出当前员工那些天休假了
     * @param selectEmployeeId
     * @return
     */
    List selectVacationByFiscalYear(@Param("selectEmployeeId")Integer selectEmployeeId, 
                                    @Param("fiscalYearStart")Date fiscalYearStart,
                                    @Param("fiscalYearEnd")Date fiscalYearEnd);

    /**
     * 根据财年号和员工id分别查询查询在此财年休了几个半天，几个一天
     * @param selectEmployeeId
     * @param fiscalYearStart
     * @param fiscalYearEnd
     * @return
     */
    Integer selectVacationByFullDay(@Param("selectEmployeeId")Integer selectEmployeeId,
                                    @Param("fiscalYearStart")Date fiscalYearStart,
                                    @Param("fiscalYearEnd")Date fiscalYearEnd);
    Integer selectVacationByHalfDay(@Param("selectEmployeeId")Integer selectEmployeeId,
                                    @Param("fiscalYearStart")Date fiscalYearStart,
                                    @Param("fiscalYearEnd")Date fiscalYearEnd);
}