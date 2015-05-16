package com.ebrun.holiday.dao;

import com.ebrun.holiday.model.Vacation;

public interface VacationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vacation record);

    int insertSelective(Vacation record);

    Vacation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vacation record);

    int updateByPrimaryKey(Vacation record);
}