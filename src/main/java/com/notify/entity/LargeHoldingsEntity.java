package com.notify.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "large_holdings")
@Getter
public class LargeHoldingsEntity {
    @Id
    private long rceptNo;

    private String corpCode;
    private String corpName;
    private String repror;
    private long stkqy; // 보유주식 수
    private long stkqyIrds; // 보유주식등의 증감
    private String reportResn; // 보고 사유
    private String rceptDt; // 접수 일자(YYYYMMDD)
    private String regDt;
}
