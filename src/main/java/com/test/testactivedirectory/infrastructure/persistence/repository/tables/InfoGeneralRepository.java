package com.test.testactivedirectory.infrastructure.persistence.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.DatosDept;
import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.infGeneral;



public interface InfoGeneralRepository extends JpaRepository <infGeneral, Integer> {


}