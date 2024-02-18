package com.aone.poc.dbtocsvfile.model.entity;

import com.aone.poc.dbtocsvfile.opencsv.CsvBindByNameOrder;
import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dog")
@CsvBindByNameOrder(value = {"id", "name", "age", "breed"})
public class Dog extends Animal {

    @CsvBindByName(column = "bread")
    @Column(name = "bread")
    private String breed;

}
