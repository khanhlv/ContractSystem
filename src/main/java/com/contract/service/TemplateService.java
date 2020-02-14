package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.form.TemplateForm;
import com.contract.model.Template;
import com.contract.model.pojo.TemplatePojo;
import com.contract.service.repository.TemplateRepository;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager em;

    public List<Template> findTemplateByStatus(Long status){
        return templateRepository.findTemplateByStatus(status).stream().collect(Collectors.toList());
    }

    public Long selectPagingCount(TemplateForm templateForm) {
        try {
            em = emf.createEntityManager();

            Query query = em.createNativeQuery("SELECT count(1) FROM TEMPLATE t " +
                    "INNER JOIN CATEGORY c ON t.CATEGORY_ID = t.CATEGORY_ID " +
                    "INNER JOIN COMPANY cp ON cp.COMPANY_ID = t.COMPANY_ID " +
                    "WHERE (:templateName = '' OR t.TEMPLATE_NAME LIKE :templateName) " +
                    "AND (:status = '-1' OR t.STATUS = :status)");

            buildParam(query, templateForm);

            Integer count = (Integer) query.getSingleResult();

            return Long.valueOf(count);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void buildParam(Query query, TemplateForm templateForm) {
        query.setParameter("templateName", "%" + StringUtils.defaultString(templateForm.getTemplateName()) + "%" );
        query.setParameter("status", templateForm.getStatus() == null ? -1L : templateForm.getStatus());
    }

    public List<TemplatePojo> selectPaging(TemplateForm templateForm)
    {
        Long count = selectPagingCount(templateForm);
        templateForm.setTotalRecord(count);

        try {
            em = emf.createEntityManager();

            Query query = em.createNativeQuery("SELECT t.*, c.CATEGORY_NAME, cp.COMPANY_NAME, " +
                    "(SELECT COUNT(1) FROM TEMPLATE_FILE tf WHERE tf.TEMPLATE_ID = t.TEMPLATE_ID) AS FILE_COUNT FROM TEMPLATE t " +
                    "INNER JOIN CATEGORY c ON t.CATEGORY_ID = t.CATEGORY_ID " +
                    "INNER JOIN COMPANY cp ON cp.COMPANY_ID = t.COMPANY_ID " +
                    "WHERE (:templateName = '' OR t.TEMPLATE_NAME LIKE :templateName) " +
                    "AND (:status = '-1' OR t.STATUS = :status) " +
                    "ORDER BY t.TEMPLATE_ID OFFSET :pageSize ROWS FETCH NEXT :limit ROWS ONLY", TemplatePojo.class);

            query.setParameter("pageSize", (templateForm.getPage().intValue() == 0 ? 0 : templateForm.getPage().intValue() - 1) * templateForm.getLimit());
            query.setParameter("limit", templateForm.getLimit());

            buildParam(query, templateForm);

            List<TemplatePojo> list = query.getResultList();

            return list;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public TemplateRepository getTemplateRepository() {
        return templateRepository;
    }
}
