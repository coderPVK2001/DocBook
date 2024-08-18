package com.docbook.city.service;
import com.docbook.city.entity.City;
import com.docbook.city.payload.CityDto;

public interface CityService {

    public City add(CityDto dto);

    public City update(long id, CityDto dto);
}
