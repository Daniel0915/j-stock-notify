package com.notify.entity.dto.LargeHoldings;

import com.notify.entity.LargeHoldingsEntity;
import com.notify.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class LHResponseDTO {
    private String status;
    private String message;
    private List<ListDTO> list;

    @Getter
    public static class ListDTO {
        private String rcept_no;
        private String corp_code;
        private String corp_name;
        private String repror;
        private String stkqy; // 보유주식 수
        private String stkqy_irds; // 보유주식등의 증감
        private String report_resn; // 보고 사유
        private String rcept_dt; // 접수 일자(YYYYMMDD)
    }

    public List<LargeHoldingsEntity> toListLargeHoldingsEntity() {
        list = list.stream()
                .sorted(Comparator.comparing(LHResponseDTO.ListDTO::getRcept_no))
                .collect(
                Collectors.toList());

        List<LargeHoldingsEntity> largeHoldingsEntityList = new ArrayList<>();

        for (ListDTO listDTO : list) {
            largeHoldingsEntityList.add(LargeHoldingsEntity.builder()
                            .rceptNo(Long.parseLong(listDTO.rcept_no))
                            .corpCode(listDTO.corp_code)
                            .corpName(listDTO.corp_name)
                            .repror(listDTO.repror)
                            .stkqy(Long.parseLong(listDTO.stkqy.replaceAll(",","")))
                            .stkqyIrds(Long.parseLong(listDTO.stkqy_irds.replaceAll(",","")))
                            .reportResn(listDTO.report_resn)
                            .rceptDt(listDTO.rcept_dt)
                            .regDt(Date.nowTimeTo("yyyyMMddHHmmss"))
                            .build()
            );
        }
        return largeHoldingsEntityList;
    }
}
