package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.CityRequestDTO;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.service.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OpenWeatherServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private OpenWeatherService openWeatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCity_ValidCity_ReturnsCityFromRepository() {
        String cityName = "Budapest";
        City expectedCity = new City();
        expectedCity.setName(cityName);

        when(cityRepository.findByNameIgnoreCase(cityName)).thenReturn(Optional.of(expectedCity));

        City result = openWeatherService.getCity(cityName);

        assertEquals(expectedCity, result);
        verify(cityRepository, times(1)).findByNameIgnoreCase(cityName);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void testUpdateCity_InvalidCity_ThrowsException() {
        String cityName = "InvalidCity";
        CityRequestDTO cityDTO = new CityRequestDTO(111, 111, "Country", null, cityName);

        when(cityRepository.findByNameIgnoreCase(cityName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> openWeatherService.updateCity(cityName, cityDTO));

        verify(cityRepository, times(1)).findByNameIgnoreCase(cityName);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void testUpdateCity_ValidCity_UpdatesCityInRepository() {
        String cityName = "Budapest";
        CityRequestDTO cityDTO = new CityRequestDTO(111, 111, "Country", null, cityName);
        City existingCity = new City();
        existingCity.setName(cityName);

        when(cityRepository.findByNameIgnoreCase(cityName)).thenReturn(Optional.of(existingCity));

        openWeatherService.updateCity(cityName, cityDTO);

        verify(cityRepository, times(1)).findByNameIgnoreCase(cityName);
        verify(cityRepository, times(1)).save(existingCity);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void testDeleteCity_ValidCity_DeletesCityFromRepository() {
        String cityName = "Budapest";

        openWeatherService.deleteCity(cityName);

        verify(cityRepository, times(1)).deleteByNameIgnoreCase(cityName);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void testSaveCity_ValidCity_SavesCityToRepository() {
        CityRequestDTO cityDTO = new CityRequestDTO(111, 111, "Country", null, "Budapest");

        when(cityRepository.save(any())).thenReturn(new City());

        City result = openWeatherService.saveCity(cityDTO);

        assertNotNull(result);
        verify(cityRepository, times(1)).save(any());
        verifyNoMoreInteractions(cityRepository);
    }
}
