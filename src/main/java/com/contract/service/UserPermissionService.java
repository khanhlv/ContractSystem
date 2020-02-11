package com.contract.service;

import com.contract.model.UserGroup;
import com.contract.model.UserPermission;
import com.contract.service.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public List<UserPermission> findAllWithUserGroup(UserGroup userGroup){
        return userPermissionRepository.findAllWithUserGroup(userGroup.getUserGroupId()).stream().collect(Collectors.toList());
    }

    public UserPermissionRepository getUserPermissionRepository() {
        return userPermissionRepository;
    }
}
