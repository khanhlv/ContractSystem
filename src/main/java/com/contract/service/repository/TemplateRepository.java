package com.contract.service.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.contract.model.Template;
import com.contract.model.pojo.TemplatePojo;

public interface TemplateRepository extends CrudRepository<Template, Long> {
    Collection<Template> findTemplateByStatus(Long status);

    @Query(
            value = "SELECT t.TEMPLATE_ID as templateId, " +
                    "t.TEMPLATE_NAME as templateName, " +
                    "t.TEMPLATE_DESCRIPTION as templateDescription, " +
                    "t.CATEGORY_ID as categoryId, " +
                    "t.COMPANY_ID as companyId, " +
                    "t.CREATED_DATE as createdDate, " +
                    "t.STATUS as status, " +
                    "c.CATEGORY_NAME as categoryName, " +
                    "cp.COMPANY_NAME as companyName, " +
                    "(SELECT COUNT(1) FROM TEMPLATE_FILE tf WHERE tf.TEMPLATE_ID = t.TEMPLATE_ID) AS fileCount FROM TEMPLATE t " +
                    "INNER JOIN CATEGORY c ON c.CATEGORY_ID = t.CATEGORY_ID " +
                    "INNER JOIN COMPANY cp ON cp.COMPANY_ID = t.COMPANY_ID " +
                    "WHERE (:templateName = '' OR t.TEMPLATE_NAME LIKE :templateName) " +
                    "AND (:status = '-1' OR t.STATUS = :status) ",
            nativeQuery = true)
    Page<TemplatePojo> findAllWithPagination(@Param("templateName") String templateName,
                                             @Param("status") Long status, Pageable pageable);
}

