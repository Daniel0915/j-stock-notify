package com.notify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class represents the ownership information of an executive.
 */
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exec_ownership")
public class ExecOwnershipEntity {

    @Id
    private long rceptNo;

    private String corpCode;
    private String corpName;
    private String repror;
    private String isuExctvRgistAt;
    private String isuExctvOfcps;
    private String isuMainShrholdr;
    private long spStockLmpCnt;
    private long spStockLmpIrdsCnt;
    private String spStockLmpRate;
    private String spStockLmpIrdsRate;
    private String rceptDt;
    private String regDt;
}
