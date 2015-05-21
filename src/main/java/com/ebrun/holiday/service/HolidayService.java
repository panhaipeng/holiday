package com.ebrun.holiday.service;

import java.util.Date;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/20.
 */
public interface HolidayService {
    /**
     * 根据入职日期，计算当前财年共有多少天年假
     * @param entryDate
     * @return
     */
    public Integer calculateHolidaysByEntryDate(Date entryDate);
    /**
     * map<财年号:年假天数>
     */
    public Map<String,Integer> calculateHolidaysMap(Date entryDate,Date leaveDate);
}
