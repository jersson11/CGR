package com.test.testactivedirectory.infrastructure.persistence.entity.Menu;

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
@Table(name = "submenus")
@NoArgsConstructor
public class SubMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    private Menu menu;

}
