package com.contract.model;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATE", schema = "dbo")
public class Template extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TEMPLATE_ID")
    private Long templateId;

    @Column(name = "TEMPLATE_NAME")
    private String templateName;

    @Column(name = "TEMPLATE_DESCRIPTION")
    private String templateDescription;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
