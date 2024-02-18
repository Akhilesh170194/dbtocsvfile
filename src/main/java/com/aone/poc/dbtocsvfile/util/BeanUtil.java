package com.aone.poc.dbtocsvfile.util;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.aone.poc.dbtocsvfile.model.entity.Animal;
import com.aone.poc.dbtocsvfile.model.repository.AnimalRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BeanUtil {

    private final Repositories repositories;

    public BeanUtil(ApplicationContext applicationContext) {
        this.repositories = new Repositories(applicationContext);
    }

    @SuppressWarnings("unchecked")
    public <T extends Animal> AnimalRepository<T> getRepository(Class<T> entity) {

        Assert.notNull(entity, "entity class must not be null");
        Optional<Object> repository = repositories.getRepositoryFor(entity);
        if (repository.isPresent()) {
            return (AnimalRepository<T>) repository.get();
        }
        log.warn("Repository not found for Entity {}.", entity.getCanonicalName());
        return null;
    }

}
