package com.contract.form;

import com.contract.consts.WebConsts;
import com.contract.model.Category;

public class CategoryForm extends Category {
    private Long page = 0L;

    private Long limit = WebConsts.PAGE_SIZE;

    private Long totalRecord;

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
}
