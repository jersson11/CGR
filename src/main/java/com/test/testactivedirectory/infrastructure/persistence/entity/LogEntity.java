package com.test.testactivedirectory.infrastructure.persistence.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "logs")
public class LogEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank
    @Column(name = "name_user", nullable=false, length = 100)
    private String name_user;

    @NotBlank
    @Column(name = "email", nullable=false, length = 100)
    private String correo;

    @NotBlank
    @Column(name = "date_session_start", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date data_session_start;

    @Column(name = "enable", nullable=false)
    private boolean enable;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

}
