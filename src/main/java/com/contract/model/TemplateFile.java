package com.contract.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATE_FILE", schema = "dbo")
public class TemplateFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TEMPLATE_FILE_ID")
    private Long templateFileId;

    @Column(name = "TEMPLATE_ID")
    private Long templateId;

    @Column(name = "TEMPLATE_FILE_NAME")
    private String templateFileName;

    @Column(name = "TEMPLATE_FILE_PATH")
    private String templateFilePath;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "CREATED_USER_ID")
    private Long createdUserId;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getTemplateFileId() {
        return templateFileId;
    }

    public void setTemplateFileId(Long templateFileId) {
        this.templateFileId = templateFileId;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }
}
