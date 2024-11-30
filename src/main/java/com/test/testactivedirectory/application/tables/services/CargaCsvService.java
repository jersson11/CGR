package com.test.testactivedirectory.application.tables.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.DatosDept;
import com.test.testactivedirectory.infrastructure.persistence.repository.tables.MiEntidadRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class CargaCsvService {

    @Autowired
    private MiEntidadRepository miEntidadRepository;

    public void cargarCsv(MultipartFile archivo) {
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8));
                CSVParser parser = new CSVParser(lector, CSVFormat.DEFAULT
                        .withDelimiter(';') // Especificar el delimitador
                        .withHeader() // Usar la primera línea como encabezado
                        .withSkipHeaderRecord())) {

            for (CSVRecord record : parser) {
                DatosDept entidad = new DatosDept();
                entidad.setCodigoString(record.get("CODIGO"));
                // entidad.setCodigoString(record.get("CODIGO"));
                entidad.setDepartamentos(record.get("DEPARTAMENTO"));

                
                System.out.println(entidad);

                miEntidadRepository.save(entidad);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo CSV: " + e.getMessage());
        }
    }
}
