package com.ebrun.holiday.controller;

import com.ebrun.holiday.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaoDao on 2015/5/22.
 */
@Controller
public class HolidayController {

    private HolidayService holidayService;

    public HolidayService getHolidayService() {
        return holidayService;
    }

    @Autowired
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
    @RequestMapping(value = "/showHoliday",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showHoliday(@RequestParam("selectEmployeeId")Integer selectEmployeeId,@RequestParam("fiscalYear")String fiscalYear){
        Map<String,Object>map=new HashMap<>();
        holidayService.showHoliday(selectEmployeeId,fiscalYear);
        return map;
    }
}
