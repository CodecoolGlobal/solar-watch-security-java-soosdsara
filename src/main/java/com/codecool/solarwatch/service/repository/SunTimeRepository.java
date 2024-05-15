package com.codecool.solarwatch.service.repository;

import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface SunTimeRepository extends JpaRepository<SunTime, Long> {

    Optional<SunTime> findByCityAndDate(City city, LocalDate date);

}
