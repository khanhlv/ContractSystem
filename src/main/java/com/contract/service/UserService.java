package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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

        Page<User> page = userRepository
                .findAllWithPagination(
                        StringUtils.defaultString(userForm.getUsername(), StringUtils.EMPTY),
                        StringUtils.defaultString(userForm.getEmail(), StringUtils.EMPTY),
                        StringUtils.defaultString(userForm.getPhone(), StringUtils.EMPTY),
                        userForm.getCompany().getCompanyId() == null ? -1L : userForm.getCompany().getCompanyId(),
                        userForm.getUserGroup().getUserGroupId() == null ? -1L : userForm.getUserGroup().getUserGroupId(),
                        userForm.getStatus() == null ? -1L : userForm.getStatus(),
                        pageable);
        userForm.setTotalRecord(page.getTotalElements());

        return page.stream().collect(Collectors.toList());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
