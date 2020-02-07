package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {
    
}