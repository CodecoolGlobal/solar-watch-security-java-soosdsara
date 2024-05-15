package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.InvalidCityException;
import com.codecool.solarwatch.model.CityDTO;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.service.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenWeatherService {

    private static final String API_KEY = "83d6cc9ec9ff9628f3f9ce3ffaf0fe1c";
    private static final String OPENWEATHER_API_URL = "https://api.openweathermap.org/geo/1.0/direct";
    private final WebClient webClient;
    private final CityRepository cityRepository;
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);

    @Autowired
    public OpenWeatherService(WebClient webClient, CityRepository cityRepository) {
        this.webClient = webClient;
        this.cityRepository = cityRepository;
    }


    public City getCity(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseGet(() -> saveCity(cityName));
    }

    private City saveCity(String cityName) {

        CityDTO cityDTO = this.getLatitudeAndLongitude(cityName);

        City city = new City();
        city.setName(cityDTO.name());
        city.setCountry(cityDTO.country());
        city.setState(cityDTO.state());
        city.setLatitude(cityDTO.lat());
        city.setLongitude(cityDTO.lon());

        return cityRepository.save(city);
    }

    private CityDTO getLatitudeAndLongitude(String city) {
        String url = String.format("%s?q=%s&limit=1&appid=%s", OPENWEATHER_API_URL, city, API_KEY);

        CityDTO[] response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(CityDTO[].class)
                .block();

        if (response == null || response.length == 0) {
            throw new InvalidCityException("Invalid city name: " + city);
        }
        return response[0];
    }

}
