package com.contract.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.contract.model.Template;

@Entity
public class TemplatePojo extends Template {

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "FILE_COUNT")
    private Long fileCount;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }
}
