package com.contract.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTRACT", schema = "dbo")
public class Contract extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "CONTRACT_CODE")
    private String contractCode;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
}
