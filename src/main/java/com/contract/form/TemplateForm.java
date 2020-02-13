package com.contract.form;

import org.springframework.web.multipart.MultipartFile;

import com.contract.consts.WebConsts;
import com.contract.model.Template;

public class TemplateForm extends Template {
    private Long page = 0L;

    private Long limit = WebConsts.PAGE_SIZE;

    private Long totalRecord;

    private MultipartFile[] files;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
