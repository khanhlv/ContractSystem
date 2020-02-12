package com.contract.service.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.contract.model.Template;

public interface TemplateRepository extends CrudRepository<Template, Long> {
    Collection<Template> findTemplateByStatus(Long status);

    @Query(
            value = "SELECT * FROM [TEMPLATE] " +
                    "WHERE (:templateName = '' OR TEMPLATE_NAME LIKE :templateName) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            nativeQuery = true)
    Page<Template> findAllWithPagination(@Param("templateName") String templateName,
                                         @Param("status") Long status, Pageable pageable);
}