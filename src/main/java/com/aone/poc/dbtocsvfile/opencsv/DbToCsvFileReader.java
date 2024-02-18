package com.aone.poc.dbtocsvfile.opencsv;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.aone.poc.dbtocsvfile.model.entity.Animal;
import com.aone.poc.dbtocsvfile.model.repository.AnimalRepository;
import com.aone.poc.dbtocsvfile.util.BeanUtil;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DbToCsvFileReader<T extends Animal> {

    private AnimalRepository<T> animalRepository;
    private CsvHeaderColumMappingStrategy<T> columMappingStrategy;

    public DbToCsvFileReader(Class<T> type, BeanUtil beanUtil) {
        this.animalRepository = beanUtil.getRepository(type);
        this.columMappingStrategy = new CsvHeaderColumMappingStrategy<>(type);
    }

    public StreamingResponseBody readDatabaseAndCreateCsvFile() {

        return outputStream -> {
            List<T> animals = animalRepository.findAll();
            log.info("total number of animals adding in csv file is {}", animals.size());
            try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                try {
                    new StatefulBeanToCsvBuilder<T>(writer).withMappingStrategy(columMappingStrategy)
                            .build().write(animals);
                } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        };
    }
}
