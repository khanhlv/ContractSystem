package com.contract.model;

import java.util.List;

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

    @OneToMany(mappedBy="template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateFile> templateFiles;

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

    public List<TemplateFile> getTemplateFiles() {
        return templateFiles;
    }

    public void setTemplateFiles(List<TemplateFile> templateFiles) {
        this.templateFiles = templateFiles;
    }
}
