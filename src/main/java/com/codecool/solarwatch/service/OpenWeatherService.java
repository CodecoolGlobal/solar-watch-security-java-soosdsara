package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.InvalidCityException;
import com.codecool.solarwatch.model.dto.CityRequestDTO;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.service.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.NoSuchElementException;

@Service
public class OpenWeatherService {

    private static final String API_KEY = "83d6cc9ec9ff9628f3f9ce3ffaf0fe1c";
    private static final String OPENWEATHER_API_URL = "https://api.openweathermap.org/geo/1.0/direct";
    private final WebClient webClient;
    private final CityRepository cityRepository;
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);

    public OpenWeatherService(WebClient webClient, CityRepository cityRepository) {
        this.webClient = webClient;
        this.cityRepository = cityRepository;
    }

    public City getCity(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseGet(() -> fetchNewCityToDB(cityName));
    }

    @Transactional
    public void updateCity(String cityName, CityRequestDTO cityDTO) {
        City city = cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new NoSuchElementException(cityName));

        setCityData(city, cityDTO);

        cityRepository.save(city);
        logger.info("City updated successfully: {}", cityName);
    }

    @Transactional
    public void deleteCity(String cityName) {
        cityRepository.deleteByNameIgnoreCase(cityName);
        logger.info("City deleted successfully: {}", cityName);
    }

    private City fetchNewCityToDB(String cityName) {
        CityRequestDTO cityDTO = getLatitudeAndLongitude(cityName);
        logger.info("New city information saved for: {}", cityName);
        return saveCity(cityDTO);
    }

    public City saveCity(CityRequestDTO cityDTO) {
        City city = new City();
        setCityData(city, cityDTO);
        logger.info("City saved: {}", city.getName());
        return cityRepository.save(city);
    }

    private void setCityData(City city, CityRequestDTO cityDTO) {
        city.setName(cityDTO.name());
        city.setCountry(cityDTO.country());
        city.setState(cityDTO.state());
        city.setLatitude(cityDTO.lat());
        city.setLongitude(cityDTO.lon());
    }

    private CityRequestDTO getLatitudeAndLongitude(String city) {
        String url = String.format("%s?q=%s&limit=1&appid=%s", OPENWEATHER_API_URL, city, API_KEY);

        CityRequestDTO[] response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(CityRequestDTO[].class)
                .block();

        if (response == null || response.length == 0) {
            logger.error("Invalid city name received from API: {}", city);
            throw new InvalidCityException("Invalid city name: " + city);
        }
        logger.info("City data fetched successfully from API for city: {}", city);
        return response[0];
    }
}