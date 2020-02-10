package com.contract.service.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.contract.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    Collection<Company> findByStatus(Long status);

    @Query(
            value = "SELECT * FROM [COMPANY] " +
                    "WHERE (:companyName = '' OR COMPANY_NAME LIKE :companyName) " +
                    "AND (:companyPhone = '' OR COMPANY_PHONE = :companyPhone) " +
                    "AND (:companyEmail = '' OR COMPANY_EMAIL = :companyEmail) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            countQuery = "SELECT count(1) FROM [COMPANY] " +
                    "WHERE (:companyName = '' OR COMPANY_NAME LIKE :companyName) " +
                    "AND (:companyPhone = '' OR COMPANY_PHONE = :companyPhone) " +
                    "AND (:companyEmail = '' OR COMPANY_EMAIL = :companyEmail) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            nativeQuery = true)
    Page<Company> findAllWithPagination(@Param("companyName") String companyName,
                                        @Param("companyPhone") String companyPhone,
                                        @Param("companyEmail") String companyEmail,
                                        @Param("status") Long status, Pageable pageable);
}