package com.docbook.area.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.docbook.area.entity.Area;
import com.docbook.area.exception.AreaNotFoundException;
import com.docbook.area.payload.AreaDto;
import com.docbook.area.repository.AreaRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {

    private AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public AreaDto add(AreaDto areaDto) {

        //create a new area object
        Area area=new Area();
        area.setAreaname(areaDto.getName());
        Area saved = areaRepository.save(area);

        AreaDto dto=new AreaDto();
        dto.setName(saved.getAreaname());
        return dto;
    }

    @Override
    public AreaDto update(Long id, AreaDto areaDto) {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("area not found!!"));
        area.setAreaname(areaDto.getName());
        Area saved = areaRepository.save(area);

        AreaDto dto=new AreaDto();
        dto.setName(saved.getAreaname());
        return dto;
    }

    @Override
    public void delete(String name) {

        areaRepository.findByAreaname(name).orElseThrow(() -> new AreaNotFoundException("area not found!!"));
        areaRepository.deleteByAreaname(name);

    }
}
//for deleting By JPQL
//@Transactional:
//Ensures that the delete operation is executed within a transaction.
//This means that the changes (deletion) are committed to the database only if the entire transaction is successful.
// Without it, the operation might not be committed.

//@Modifying:
// Indicates that the query is an update or delete operation, not a select.
// It tells Spring Data JPA to execute the query in a way that modifies the database.
