package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.SunTimeApiResponseDTO;
import com.codecool.solarwatch.model.dto.SunTimeReportDTO;
import com.codecool.solarwatch.model.dto.SunTimeRequestDTO;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunTime;
import com.codecool.solarwatch.service.repository.SunTimeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.NoSuchElementException;

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
                .orElseGet(() -> fetchSunTimeToDB(city, date));

        return new SunTimeReportDTO(sunTime.getSunrise(), sunTime.getSunset());
    }

    @Transactional
    public void updateSunTime(String cityName, LocalDate date, SunTimeRequestDTO sunTimeDTO) {
        City oldCity = openWeatherService.getCity(cityName);
        City newCity = openWeatherService.getCity(sunTimeDTO.city());
        SunTime sunTime = sunTimeRepository.findByCityAndDate(oldCity, date)
                .orElseThrow(() -> new NoSuchElementException("No sun time data found for " + cityName + " on " + date));

        updateSunTimeData(sunTime, newCity, sunTimeDTO);
        sunTimeRepository.save(sunTime);
        logger.info("Updated sun time data for city: {}, date: {}", cityName, date);
    }

    @Transactional
    public void deleteSunTime(String cityName, LocalDate date) {
        City city = openWeatherService.getCity(cityName);
        sunTimeRepository.deleteByCityAndDate(city, date);
        logger.info("Deleted sun time data for city: {}, date: {}", cityName, date);
    }

    @Transactional
    public void createSunTime(SunTimeRequestDTO sunTimeDTO) {
        City city = openWeatherService.getCity(sunTimeDTO.city());

        SunTime sunTime = new SunTime();
        updateSunTimeData(sunTime, city, sunTimeDTO);
        sunTimeRepository.save(sunTime);
        logger.info("Created new sun time data for city: {}, date: {}", sunTimeDTO.city(), sunTimeDTO.date());
    }

    private SunTime fetchSunTimeToDB(City city, LocalDate date) {
        SunTimeApiResponseDTO response = fetchSunTimeData(city, date);

        SunTime sunTime = new SunTime();
        sunTime.setCity(city);
        sunTime.setDate(date);
        sunTime.setSunrise(response.results().sunrise());
        sunTime.setSunset(response.results().sunset());
        logSunTimes(sunTime);

        return sunTimeRepository.save(sunTime);
    }

    private SunTimeApiResponseDTO fetchSunTimeData(City city, LocalDate date) {
        String url = buildApiUrl(city, date);

        SunTimeApiResponseDTO response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(SunTimeApiResponseDTO.class)
                .block();

        if (response == null || response.results() == null) {
            throw new RuntimeException("Error fetching sun time for " + city.getName());
        }
        return response;
    }

    private String buildApiUrl(City city, LocalDate date) {
        return String.format("%s?lat=%s&lng=%s&date=%s&formatted=1", SUNTIME_API_URL, city.getLatitude(), city.getLongitude(), date);
    }

    private void updateSunTimeData(SunTime sunTime, City city, SunTimeRequestDTO sunTimeDTO) {
        sunTime.setCity(city);
        sunTime.setDate(LocalDate.parse(sunTimeDTO.date()));
        sunTime.setSunrise(sunTimeDTO.sunrise());
        sunTime.setSunset(sunTimeDTO.sunset());
    }

    private void logSunTimes(SunTime sunTime) {
        logger.info("Sunrise: {}, Sunset: {}", sunTime.getSunrise(), sunTime.getSunset());
    }
}