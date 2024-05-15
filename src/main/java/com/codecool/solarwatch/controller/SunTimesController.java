package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.SunTimeReportDTO;
import com.codecool.solarwatch.service.SunTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class SunTimesController {
    private final SunTimeService sunTimeService;

    @Autowired
    public SunTimesController(SunTimeService sunTimeService) {
        this.sunTimeService = sunTimeService;
    }

    @GetMapping("/sun-times/{city}/{dateStr}")
    public SunTimeReportDTO getSunTimes(@PathVariable String city, @PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return sunTimeService.getSunTime(city, date);
    }

}

