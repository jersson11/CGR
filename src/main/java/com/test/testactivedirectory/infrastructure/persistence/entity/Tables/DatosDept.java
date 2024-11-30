package com.test.testactivedirectory.infrastructure.persistence.entity.Tables;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "datosDept")
@NoArgsConstructor

public class DatosDept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  codigo;

    private String codigoString;

    @Column(nullable = false)
    private String departamentos;
    // @ManyToOne
    // @JoinColumn(name = "menu_id", nullable = false)
    // @JsonBackReference
    // private datosDept datosDept;

    public void setCodigoString(String str) {
        this.codigoString = str;
    };

}
