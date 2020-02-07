package com.contract.model;

import javax.persistence.*;

@Entity
@Table(name = "SETTING", schema = "dbo")
public class Setting extends BaseModel {
    @Id
    @Column(name = "SETTING_KEY")
    private String settingKey;

    @Column(name = "SETTING_VALUE")
    private String settingValue;

    @Column(name = "SETTING_GROUP")
    private String settingGroup;

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingGroup() {
        return settingGroup;
    }

    public void setSettingGroup(String settingGroup) {
        this.settingGroup = settingGroup;
    }
}
