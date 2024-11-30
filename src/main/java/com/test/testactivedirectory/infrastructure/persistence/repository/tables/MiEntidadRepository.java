package com.test.testactivedirectory.infrastructure.persistence.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.DatosDept;

public interface MiEntidadRepository extends JpaRepository <DatosDept, Integer> {


}