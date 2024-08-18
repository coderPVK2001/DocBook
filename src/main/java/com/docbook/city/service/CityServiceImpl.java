package com.docbook.city.service;

import com.docbook.city.entity.City;
import com.docbook.city.payload.CityDto;
import com.docbook.city.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City add(CityDto dto){
        City city=new City();
        city.setCityname(dto.getName());
        return  cityRepository.save(city);

    };

    @Override
    public City update(long id, CityDto dto) {

        Optional<City> optional = cityRepository.findById(id);
        if (optional.isPresent()) {
            City city = optional.get();
            city.setCityname(dto.getName());
            return cityRepository.save(city);
        }
        else{
            return null;
        }
    }


}
