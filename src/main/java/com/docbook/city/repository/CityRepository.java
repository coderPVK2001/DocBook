package com.docbook.city.repository;

import com.docbook.area.entity.Area;
import com.docbook.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByCityname(String name);
}