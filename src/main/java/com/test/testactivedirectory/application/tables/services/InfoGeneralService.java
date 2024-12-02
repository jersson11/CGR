package com.test.testactivedirectory.application.tables.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.DatosDept;
import com.test.testactivedirectory.infrastructure.persistence.entity.Tables.infGeneral;
import com.test.testactivedirectory.infrastructure.persistence.repository.tables.InfoGeneralRepository;
import com.test.testactivedirectory.infrastructure.persistence.repository.tables.MiEntidadRepository;
@Service
public class InfoGeneralService {
    @Autowired
    private InfoGeneralRepository infoGeneralRepository;

    public void cargaGen(MultipartFile archivo) {
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8));
                CSVParser parser = new CSVParser(lector, CSVFormat.DEFAULT
                        .withDelimiter(';') // Especificar el delimitador
                        .withHeader() // Usar la primera línea como encabezado
                        .withSkipHeaderRecord())) {

            for (CSVRecord record : parser) {
                infGeneral entidad = new infGeneral();
                entidad.setCodigo(record.get("CODIGO"));
                entidad.setNombre(record.get("NOMBRE"));
                entidad.setCPC(record.get("CPC"));
                entidad.setDetalleSectorial(record.get("DETALLE_SECTORIAL"));
                entidad.setFuentesFinanciacion(record.get("FUENTES_DE_FINANCIACION"));
                entidad.setTerceros(record.get("TERCEROS"));
                entidad.setPoliticaPublica(record.get("POLITICA_PUBLICA"));
                entidad.setNumFechNorma(record.get("NUMERO_Y_FECHA_DE_LA_NORMA"));
                entidad.setTipoNorma(record.get("TIPO_DE_NORMA"));
                entidad.setReacau1(record.get("RECAUDO_VIGEN_ACTUAL_SIN_FONDOS"));
                entidad.setReacau2(record.get("RECAUDO_VIGEN_ACTUAL_CON_FONDOS"));
                entidad.setReacau3(record.get("RECAUDO_VIGEN_ANTERIOR_SIN_FONDO"));
                entidad.setReacau4(record.get("RECAUDO_VIGEN_ANTERIOR_CON_FONDO"));
                entidad.setTotalRecaudo(record.get("TOTAL_RECAUDO"));
               

                
                System.out.println(entidad);
          
                infoGeneralRepository.save(entidad);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error al procesar el archivo CSV:" + e);
        }
    }
    
}
