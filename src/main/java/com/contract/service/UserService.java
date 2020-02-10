package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contract.form.UserForm;
import com.contract.model.User;
import com.contract.service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User checkLogin(String username, String password, Long companyId){
        return userRepository.checkLogin(username, password, companyId);
    }

    public List<User> findAllWithPagination(UserForm userForm){
        Pageable pageable = PageRequest.of(
                userForm.getPage().intValue() == 0 ? 0 : userForm.getPage().intValue() - 1,
                userForm.getLimit().intValue());

        Page<User>  pageList = userRepository
                .findAllWithPagination(pageable);
        userForm.setTotalRecord(pageList.getTotalElements());

        return pageList.stream().collect(Collectors.toList());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
