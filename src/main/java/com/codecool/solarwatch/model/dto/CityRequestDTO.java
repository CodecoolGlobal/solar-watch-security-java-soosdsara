package com.codecool.solarwatch.model.dto;

public record CityRequestDTO(double lat, double lon, String country, String state, String name) {
}
