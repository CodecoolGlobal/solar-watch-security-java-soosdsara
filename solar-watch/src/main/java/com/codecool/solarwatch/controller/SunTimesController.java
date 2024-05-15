package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.SunTimeReportDTO;
import com.codecool.solarwatch.service.SunTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SunTimesController {
    private final SunTimeService sunTimeService;

    @Autowired
    public SunTimesController(SunTimeService sunTimeService) {
        this.sunTimeService = sunTimeService;
    }

    @GetMapping("/api/sun-times/{city}/{dateStr}")
    public SunTimeReportDTO getSunTimes(@PathVariable String city, @PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return sunTimeService.getSunTime(city, date);
    }
}

