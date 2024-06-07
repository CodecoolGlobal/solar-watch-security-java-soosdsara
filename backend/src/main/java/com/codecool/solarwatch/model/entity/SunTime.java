package com.codecool.solarwatch.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class SunTime {

    @Id
    @GeneratedValue
    private long id;
    private String sunrise;
    private String sunset;
    private LocalDate date;
    @ManyToOne
    private City city;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
