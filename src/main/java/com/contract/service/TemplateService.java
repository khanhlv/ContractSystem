package com.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.service.repository.TemplateRepository;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;
}
