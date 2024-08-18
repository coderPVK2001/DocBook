package com.docbook.area.repository;

import com.docbook.area.entity.Area;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByAreaname(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Area a WHERE a.areaname=:name")
    void deleteByAreaname(@Param("name") String name);
}