package com.docbook.area.service;

import com.docbook.area.payload.AreaDto;

public interface AreaService {

    AreaDto add(AreaDto areaDto);

    AreaDto update(Long id, AreaDto areaDto);

    void delete(String name);
}
