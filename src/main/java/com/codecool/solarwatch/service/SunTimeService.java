package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.SunTimeApiResponseDTO;
import com.codecool.solarwatch.model.SunTimeReportDTO;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunTime;
import com.codecool.solarwatch.service.repository.SunTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class SunTimeService {

    private static final String SUNTIME_API_URL = "https://api.sunrise-sunset.org/json";
    private final WebClient webClient;
    private final OpenWeatherService openWeatherService;
    private final SunTimeRepository sunTimeRepository;
    private static final Logger logger = LoggerFactory.getLogger(SunTimeService.class);

    public SunTimeService(WebClient webClient, OpenWeatherService openWeatherService, SunTimeRepository sunTimeRepository) {
        this.webClient = webClient;
        this.openWeatherService = openWeatherService;
        this.sunTimeRepository = sunTimeRepository;
    }

    public SunTimeReportDTO getSunTime(String cityName, LocalDate date) {
        City city = openWeatherService.getCity(cityName);

        SunTime sunTime = sunTimeRepository.findByCityAndDate(city, date)
                .orElseGet(() -> saveSunTime(city, date));

        return new SunTimeReportDTO(sunTime.getSunrise(), sunTime.getSunset());
    }

    private SunTime saveSunTime(City city, LocalDate date) {
        String url = buildApiUrl(city, date);

        SunTimeApiResponseDTO response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(SunTimeApiResponseDTO.class)
                .block();

        if (response == null || response.results() == null) {
            throw new RuntimeException("Error fetching sun time for " + city);
        }

        SunTime sunTime = new SunTime();
        sunTime.setCity(city);
        sunTime.setDate(date);
        sunTime.setSunrise(response.results().sunrise());
        sunTime.setSunset(response.results().sunset());
        logSunTimes(sunTime);

        return sunTimeRepository.save(sunTime);
    }

    private String buildApiUrl(City city, LocalDate date) {
        return String.format("%s?lat=%s&lng=%s&date=%s&formatted=1", SUNTIME_API_URL, city.getLatitude(), city.getLongitude(), date);
    }

    private void logSunTimes(SunTime sunTimeReport) {
        logger.info("Sunrise: {}, Sunset: {}", sunTimeReport.getSunrise(), sunTimeReport.getSunset());
    }
}
