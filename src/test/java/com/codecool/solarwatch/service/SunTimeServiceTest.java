/*
package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.InvalidCityException;
import com.codecool.solarwatch.model.CityDTO;
import com.codecool.solarwatch.model.SunTimeApiResponseDTO;
import com.codecool.solarwatch.model.SunTimeReportDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SunTimeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OpenWeatherService openWeatherService;

    private SunTimeService sunTimeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sunTimeService = new SunTimeService(restTemplate, openWeatherService);
    }

    @Test
    public void testGetSunTime_Success() throws InvalidCityException {
        String city = "Budapest";
        LocalDate date = LocalDate.now();
        CityDTO location = new CityDTO(47.4979, 19.0402);
        SunTimeApiResponseDTO response = new SunTimeApiResponseDTO(new SunTimeReportDTO("6:00 AM", "7:00 PM"), "lala", "lala");
        when(openWeatherService.getLatitudeAndLongitude(city)).thenReturn(location);
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(response);

        SunTimeReportDTO sunTimeReport = sunTimeService.getSunTime(city, date);

        assertNotNull(sunTimeReport);
        assertEquals("6:00 AM", sunTimeReport.sunrise());
        assertEquals("7:00 PM", sunTimeReport.sunset());
    }

    @Test
    public void testGetSunTime_InvalidCity() {
        String city = "InvalidCity";
        LocalDate date = LocalDate.now();
        when(openWeatherService.getLatitudeAndLongitude(city)).thenReturn(null);

        assertThrows(InvalidCityException.class, () -> sunTimeService.getSunTime(city, date));
    }

}
*/
