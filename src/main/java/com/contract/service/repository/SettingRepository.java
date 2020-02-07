package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Long> {
    
}