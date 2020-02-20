package com.contract.service.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.TemplateFile;

public interface TemplateFileRepository extends CrudRepository<TemplateFile, Long> {
    Collection<TemplateFile> findByTemplateId(Long templateId);
}