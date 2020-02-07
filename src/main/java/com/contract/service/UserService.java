package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contract.model.User;
import com.contract.service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User checkLogin(String username, String password, Long companyId){
        return userRepository.checkLogin(username, password, companyId);
    }

    public List<User> findAllWithPagination(Long page, Long limit){
        Pageable pageable = PageRequest.of(page.intValue(), limit.intValue());

        return userRepository
                .findAllWithPagination(pageable)
                .stream()
                .collect(Collectors.toList());
    }
}
