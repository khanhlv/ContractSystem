package com.contract.service.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    Collection<UserGroup> findByStatus(Long status);
}