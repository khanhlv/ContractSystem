package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.model.TemplateFile;
import com.contract.service.repository.TemplateFileRepository;

@Service
public class TemplateFileService {

    @Autowired
    private TemplateFileRepository templateFileRepository;

    public List<TemplateFile> findByTemplateId(Long templateId){
        return templateFileRepository.findByTemplateId(templateId).stream().collect(Collectors.toList());
    }

    public TemplateFileRepository getTemplateFileRepository() {
        return templateFileRepository;
    }
}
