package com.aone.poc.dbtocsvfile.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.aone.poc.dbtocsvfile.model.entity.Animal;

@NoRepositoryBean
public interface AnimalRepository<T extends Animal> extends JpaRepository<T, Integer> {
    
}
