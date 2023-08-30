package com.notify.entity.dto.ExecOwnership;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class EOResponseDTO {
    private String status;
    private String message;
    private List<ListDTO> list;

    @Getter
    public static class ListDTO {
        private String rcept_no;
        private String corp_code;
        private String corp_name;
        private String repror;
        private String isu_exctv_rgist_at;
        private String isu_exctv_ofcps;
        private String isu_main_shrholdr;
        private String sp_stock_lmp_cnt;
        private String sp_stock_lmp_irds_cnt;
        private String sp_stock_lmp_rate;
        private String sp_stock_lmp_irds_rate;
        private String rcept_dt;
    }

    public List<ExecOwnershipEntity> toListExecOwnershipEntity() {
        list = list.stream()
                .sorted(Comparator.comparing(EOResponseDTO.ListDTO::getRcept_no))
                .collect(
                        Collectors.toList());

        List<ExecOwnershipEntity> execOwnershipEntityList = new ArrayList<>();

        for (ListDTO listDTO : list) {
            execOwnershipEntityList.add(ExecOwnershipEntity.builder()
                    .rceptNo(Long.parseLong(listDTO.rcept_no))
                    .corpName(listDTO.corp_name)
                    .corpCode(listDTO.corp_code)
                    .repror(listDTO.repror)
                    .isuExctvRgistAt(listDTO.isu_exctv_rgist_at)
                    .isuExctvOfcps(listDTO.isu_exctv_ofcps)
                    .isuMainShrholdr(listDTO.isu_main_shrholdr)
                    .spStockLmpCnt(
                            Long.parseLong(listDTO.sp_stock_lmp_cnt.replaceAll(",", "")))
                    .spStockLmpIrdsCnt(
                            Long.parseLong(listDTO.sp_stock_lmp_irds_cnt.replaceAll(",", "")))
                    .spStockLmpRate(listDTO.sp_stock_lmp_rate)
                    .spStockLmpIrdsRate(listDTO.sp_stock_lmp_irds_rate)
                    .rceptDt(listDTO.rcept_dt)
                    .regDt(Date.nowTimeTo("yyyyMMddHHmmss"))
                    .build()
            );
        }
        return execOwnershipEntityList;
    }
}
