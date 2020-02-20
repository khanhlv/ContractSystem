package com.contract.model.pojo;

import java.util.Date;

public interface TemplatePojo {
    Long getTemplateId();
    String getTemplateName();
    String getTemplateDescription();
    Long getCategoryId();
    Long getCompanyId();
    String getCompanyName();
    String getCategoryName();
    Long getFileCount();
    Date getCreatedDate();
    Long getStatus();
}
