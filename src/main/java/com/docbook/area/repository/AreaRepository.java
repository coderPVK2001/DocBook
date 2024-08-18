package com.docbook.area.repository;

import com.docbook.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
}