package com.ebrun.holiday.controller;

import com.ebrun.holiday.service.HolidayService;
import com.ebrun.holiday.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    private VacationService vacationService;

    public VacationService getVacationService() {
        return vacationService;
    }
@Autowired
    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @RequestMapping(value = "/showHoliday",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showHoliday(@RequestParam("selectEmployeeId")Integer selectEmployeeId,@RequestParam("fiscalYear")String fiscalYear){
        Map<String,Object>map;
        Map<String,Object>holidayMap = holidayService.showHoliday(selectEmployeeId, fiscalYear);
        map = holidayMap;
        map.put("show","success");
        return map;
    }
    @RequestMapping(value = "/showMoreFiscalYear",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showMoreFiscalYear(@RequestParam("employeeId")Integer employeeId){
        Map<String,Object> map = new HashMap<>();
        map.put("employeeId",employeeId);
        List fiscalYearList = holidayService.getFiscalYearListByEmployeeId(employeeId);
        map.put("fiscalYearList",fiscalYearList);
        map.put("showMoreFiscalYear","success");
        return map;
    }
    @RequestMapping(value="/addVacation",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addVacation(@RequestParam("employeeId")Integer employeeId,
                                          @RequestParam("vacationDate")String vacationDate,
                                          @RequestParam("vacationStatus")Integer vacationStatus){
        vacationService.insertVacation(employeeId,vacationDate,vacationStatus);
        Map<String,Object> map = new HashMap<>();
        map.put("add","success");
        return map;
    }
    @RequestMapping(value="/deleteVacation",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteVacation(@RequestParam("vacationId")Integer vacationId){
        vacationService.deleteVacationById(vacationId);
        Map<String,Object> map = new HashMap<>();
        map.put("delete","success");
        return map;
    }
}
