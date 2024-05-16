package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.dto.CityDTO;
import com.codecool.solarwatch.model.dto.SunTimeReportDTO;
import com.codecool.solarwatch.model.dto.SunTimeRequestDTO;
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

    @GetMapping("/sun-times/{cityName}/{dateStr}")
    public SunTimeReportDTO getSunTimes(@PathVariable String cityName, @PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return sunTimeService.getSunTime(cityName, date);
    }

    @PutMapping("/city/{cityName}")
    public ResponseEntity<Void> updateCity(@PathVariable String cityName, @RequestBody CityDTO cityDTO) {
        openWeatherService.updateCity(cityName, cityDTO);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/city/{cityName}")
    public ResponseEntity<Void> deleteCity(@PathVariable String cityName) {
        openWeatherService.deleteCity(cityName);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/city")
    public ResponseEntity<Void> createCity(@RequestBody CityDTO cityDTO) {
        openWeatherService.saveCity(cityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/sun-times/{cityName}/{dateStr}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateSunTime(@PathVariable String cityName, @PathVariable String dateStr, @RequestBody SunTimeRequestDTO sunTimeDTO) {
        LocalDate date = LocalDate.parse(dateStr);
        sunTimeService.updateSunTime(cityName, date, sunTimeDTO);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/sun-times/{cityName}/{dateStr}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSunTime(@PathVariable String cityName, @PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        sunTimeService.deleteSunTime(cityName, date);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/sun-times")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createSunTime(@RequestBody SunTimeRequestDTO sunTimeDTO) {
        sunTimeService.createSunTime(sunTimeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

