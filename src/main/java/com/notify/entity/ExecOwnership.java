package com.notify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exec_ownership")
public class ExecOwnership {
    @Id
    @GeneratedValue
    private int rceptNo;

    private int corpCode;
    private String corpName;
    private String repror;
    private String isuExctvRgistAt;
    private String isuExctvOfcps;
    private String isuMainShrholdr;
    private long spStockLmpCnt;
    private int spStockLmpIrdsCnt;
    private int spStockLmpRate;
    private int spStockLmpIrdsRate;
    private String rceptDt;
    private String regDt;









}
