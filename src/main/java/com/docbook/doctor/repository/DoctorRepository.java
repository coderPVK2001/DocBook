package com.docbook.doctor.repository;

import com.docbook.area.entity.Area;
import com.docbook.city.entity.City;
import com.docbook.doctor.entity.Doctor;
import com.docbook.specializations.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d where d.area=:area AND d.specialization=:specialization")
    List<Doctor> findByAreaSpecialization(@Param("area") Area area, @Param("specialization") Specialization specialization);

    @Query("SELECT d FROM Doctor d where d.city=:city AND d.specialization=:specialization")
    List<Doctor> findByCitySpecialization(@Param("city") City city, @Param("specialization") Specialization specialization1);
}