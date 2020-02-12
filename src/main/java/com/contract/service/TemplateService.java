package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contract.form.TemplateForm;
import com.contract.model.Template;
import com.contract.service.repository.TemplateRepository;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public List<Template> findTemplateByStatus(Long status){
        return templateRepository.findTemplateByStatus(status).stream().collect(Collectors.toList());
    }

    public List<Template> findAllWithPagination(TemplateForm templateForm){
        Pageable pageable = PageRequest.of(
                templateForm.getPage().intValue() == 0 ? 0 : templateForm.getPage().intValue() - 1,
                templateForm.getLimit().intValue());

        Page<Template> page = templateRepository
                .findAllWithPagination(
                        "%" + StringUtils.defaultString(templateForm.getTemplateName(), StringUtils.EMPTY) + "%",
                        templateForm.getStatus() == null ? -1L : templateForm.getStatus(),
                        pageable);
        templateForm.setTotalRecord(page.getTotalElements());

        return page.stream().collect(Collectors.toList());
    }

    public TemplateRepository getTemplateRepository() {
        return templateRepository;
    }
}
