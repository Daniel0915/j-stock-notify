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
@Table(name = "large_holdings")
public class LargeHoldings {
    @Id
    @GeneratedValue
    private int rceptNo;

    private int corpCode;
    private String corpName;
    private String repror;
    private long stkqy; // 보유주식 수
    private int stkqyIrds; // 보유주식등의 증감
    private String reportResn; // 보고 사유
    private String rceptDt; // 접수 일자(YYYYMMDD)
    private String regDt;
}
