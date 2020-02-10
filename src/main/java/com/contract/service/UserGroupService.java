package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.model.UserGroup;
import com.contract.service.repository.UserGroupRepository;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    public List<UserGroup> findByStatus(Long status){
        return userGroupRepository.findByStatus(status).stream().collect(Collectors.toList());
    }

    public UserGroupRepository getUserGroupRepository() {
        return userGroupRepository;
    }
}
