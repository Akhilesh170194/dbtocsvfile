package com.aone.poc.dbtocsvfile.model.entity;

import com.aone.poc.dbtocsvfile.opencsv.CsvBindByNameOrder;
import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat")
@CsvBindByNameOrder(value = {"id", "name", "age", "fur_color"})
public class Cat extends Animal {

    @CsvBindByName(column = "fur_color")
    @Column(name = "fur_color")
    private String furColor;

}
