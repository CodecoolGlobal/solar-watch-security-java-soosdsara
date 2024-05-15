package com.codecool.solarwatch.model;

public record SunTimeApiResponseDTO(SunTimeReportDTO results, String status, String tzid) {
}
