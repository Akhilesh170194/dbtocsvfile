package com.aone.poc.dbtocsvfile.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.aone.poc.dbtocsvfile.model.entity.Cat;
import com.aone.poc.dbtocsvfile.opencsv.DbToCsvFileReader;
import com.aone.poc.dbtocsvfile.util.BeanUtil;

@Service
public class CatService {
    
    private final DbToCsvFileReader<Cat> dbToCsvFileService;

    public CatService(BeanUtil beanUtil) {
        this.dbToCsvFileService = new DbToCsvFileReader<>(Cat.class, beanUtil);
    }

    @Transactional(readOnly = true)
    public StreamingResponseBody createCsv() {
        return dbToCsvFileService.readDatabaseAndCreateCsvFile();
    }
}
