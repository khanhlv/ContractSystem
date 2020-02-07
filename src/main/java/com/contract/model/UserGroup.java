package com.contract.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_GROUP", schema = "dbo")
public class UserGroup extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "USER_GROUP_ID")
    private Long userGroupId;

    @Column(name = "USER_GROUP_NAME")
    private String userGroupName;

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}
