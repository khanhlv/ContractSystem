package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    
}