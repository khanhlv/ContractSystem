package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contract.form.CompanyForm;
import com.contract.model.Company;
import com.contract.service.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findByStatus(Long status){
        return companyRepository.findByStatus(status).stream().collect(Collectors.toList());
    }

    public List<Company> findAllWithPagination(CompanyForm companyForm){
        Pageable pageable = PageRequest.of(
                companyForm.getPage().intValue() == 0 ? 0 : companyForm.getPage().intValue() - 1,
                companyForm.getLimit().intValue());

        Page<Company> page = companyRepository
                .findAllWithPagination(
                        "%" + StringUtils.defaultString(companyForm.getCompanyName(), StringUtils.EMPTY) + "%",
                        StringUtils.defaultString(companyForm.getCompanyPhone(), StringUtils.EMPTY),
                        StringUtils.defaultString(companyForm.getCompanyEmail(), StringUtils.EMPTY),
                        companyForm.getStatus() == null ? -1L : companyForm.getStatus(),
                        pageable);
        companyForm.setTotalRecord(page.getTotalElements());

        return page.stream().collect(Collectors.toList());
    }

    public CompanyRepository getCompanyRepository() {
        return companyRepository;
    }
}
