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
@Table(name = "infGeneral")
@NoArgsConstructor
public class infGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCodigo;
    
    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String CPC;

    @Column(nullable = false)
    private String detalleSectorial;

    @Column(nullable = false)
    private String fuentesFinanciacion;

    @Column(nullable = false)
    private String terceros;

    @Column(nullable = false)
    private String politicaPublica;

    @Column(nullable = false)
    private String numFechNorma;

    @Column(nullable = false)
    private String tipoNorma;

    @Column(nullable = false)
    private float recauVigenActualSinFondos;

    @Column(nullable = false)
    private float recauVigenActualConFondos;

    @Column(nullable = false)
    private float recauVigenAnteriorSinFondos;

    @Column(nullable = false)
    private float recauVigenAnteriorConFondos;

    @Column(nullable = false)
    private float totalRecaudo;

    // @ManyToOne
    // @JoinColumn(name = "menu_id", nullable = false)
    // @JsonBackReference
   // private infGeneral infGeneral;

}
