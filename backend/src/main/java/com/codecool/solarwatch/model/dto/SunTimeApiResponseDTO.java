package com.codecool.solarwatch.model.dto;

public record SunTimeApiResponseDTO(SunTimeReportDTO results, String status, String tzid) {
}
