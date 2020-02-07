package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.Template;

public interface TemplateRepository extends CrudRepository<Template, Long> {
    
}