package com.contract.service.repository;

import com.contract.model.UserPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserPermissionRepository extends CrudRepository<UserPermission, Long> {

    @Query("SELECT p FROM UserPermission p JOIN FETCH p.userGroup JOIN FETCH p.module  WHERE p.userGroup.userGroupId = :userGroupId")
    Collection<UserPermission> findAllWithUserGroup(@Param(value = "userGroupId") Long userGroupId);
}