package com.contract.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.UserPermission;

public interface UserPermissionRepository extends CrudRepository<UserPermission, Long> {
    
}