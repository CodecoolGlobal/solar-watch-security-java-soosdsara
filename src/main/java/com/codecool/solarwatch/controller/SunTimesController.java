package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.CityDTO;
import com.codecool.solarwatch.model.SunTimeReportDTO;
import com.codecool.solarwatch.service.OpenWeatherService;
import com.codecool.solarwatch.service.SunTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class SunTimesController {
    private final SunTimeService sunTimeService;
    private final OpenWeatherService openWeatherService;

    @Autowired
    public SunTimesController(SunTimeService sunTimeService, OpenWeatherService openWeatherService) {
        this.sunTimeService = sunTimeService;
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("/sun-times/{city}/{dateStr}")
    public SunTimeReportDTO getSunTimes(@PathVariable String city, @PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return sunTimeService.getSunTime(city, date);
    }

    @PutMapping("/sun-times/city/{cityName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateCity(@PathVariable String cityName, @RequestBody CityDTO cityDTO) {
        openWeatherService.updateCity(cityName, cityDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//sun-time data a másik service-be
}

