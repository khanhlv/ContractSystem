package com.contract.form;

import com.contract.model.Company;

public class CompanyForm extends Company {
    private Long page = 0L;

    private Long limit = 10L;

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
}
