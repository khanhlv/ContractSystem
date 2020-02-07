package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.Module;

public interface ModuleRepository extends CrudRepository<Module, Long> {
    
}