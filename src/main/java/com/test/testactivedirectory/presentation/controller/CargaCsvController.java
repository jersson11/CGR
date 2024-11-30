package com.test.testactivedirectory.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.test.testactivedirectory.application.tables.services.CargaCsvService;

@RestController
@RequestMapping("/api/csv")
public class CargaCsvController {

    @Autowired
    private CargaCsvService cargaCsvService;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarCsv(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo CSV está vacío");
        }

        try {
            cargaCsvService.cargarCsv(archivo);
            return ResponseEntity.ok("Archivo CSV cargado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }
}
