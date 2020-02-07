package com.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.service.repository.UserRepository;

@Service
public class UserGroupService {

    @Autowired
    private UserRepository userRepository;
}
