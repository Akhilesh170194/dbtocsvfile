package com.aone.poc.dbtocsvfile.model.entity;

import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "name")
    @Column(name = "name")
    private String name;

    @CsvBindByName(column = "age")
    @Column(name = "age")
    private Integer age;

}
