package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Holiday;

public interface HolidayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Holiday record);

    int insertSelective(Holiday record);

    Holiday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
}