/*
package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.DTO.CityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class OpenWeatherServiceTest {

    private RestTemplate restTemplateMock;

    private OpenWeatherService openWeatherService;

    @BeforeEach
    public void setUp() {
        this.restTemplateMock = new RestTemplate();
        openWeatherService = new OpenWeatherService(restTemplateMock);
    }

    @Test
    public void testGetLatitudeAndLongitude_Success() {
        String city = "budapest";
        CityDTO expectedLocation = new CityDTO(47.4979937, 19.0403594);
      //  when(restTemplateMock.getForObject(any(String.class), any())).thenReturn(new CityDTO[]{expectedLocation});

        CityDTO actualLocation = openWeatherService.getLatitudeAndLongitude(city);

        assertNotNull(actualLocation);
        assertEquals(expectedLocation.lat(), actualLocation.lat());
        assertEquals(expectedLocation.lon(), actualLocation.lon());
    }

    @Test
    public void testGetLatitudeAndLongitude_NullResponse() {
        String city = "budapast";
       // when(restTemplateMock.getForObject(any(String.class), any())).thenReturn(null);

        CityDTO actualLocation = openWeatherService.getLatitudeAndLongitude(city);

        assertNull(actualLocation);
    }

    @Test
    public void testGetLatitudeAndLongitude_EmptyResponse() {
        String city = "budapast";
      //  when(restTemplateMock.getForObject(any(String.class), any())).thenReturn(new CityDTO[]{});

        CityDTO actualLocation = openWeatherService.getLatitudeAndLongitude(city);

        assertNull(actualLocation);
    }
}
*/
