package com.notify.service;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.repository.ExecOwnershipRepository;
import com.notify.service.webclient.WebClientService;
import com.notify.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExecOwnershipService {

    private final WebClientService webClientExecOwnershipServiceImpl;
    private final String baseUri;
    private final String path;
    private final String corpCodeKey;
    private final String corpCodeValue;
    private final ExecOwnershipRepository execOwnershipRepository;

    @Autowired
    public ExecOwnershipService(WebClientService webClientExecOwnershipServiceImpl, @Value("${uri}") String baseUri, @Value("${uri.execOwnership}") String path,
                                @Value("${corp.code.key}") String corpCodeKey, @Value("${corp.code.value}") String corpCodeValue, ExecOwnershipRepository execOwnershipRepository) {
        this.webClientExecOwnershipServiceImpl = webClientExecOwnershipServiceImpl;
        this.baseUri = baseUri;
        this.path = path;
        this.corpCodeKey = corpCodeKey;
        this.corpCodeValue = corpCodeValue;
        this.execOwnershipRepository = execOwnershipRepository;
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void insertData() {
        List<ExecOwnershipEntity> execOwnershipEntityList = (List<ExecOwnershipEntity>) webClientExecOwnershipServiceImpl.get(baseUri, path, corpCodeKey, corpCodeValue);

        if (!execOwnershipEntityList.isEmpty()) {
            final int lastIndex = execOwnershipEntityList.size() - 1;

            final long lastRceptNo = execOwnershipEntityList.get(lastIndex).getRceptNo();

            if (!execOwnershipRepository.findByRceptNo(lastRceptNo).isPresent()) {

                List<ExecOwnershipEntity> nowEntity = execOwnershipRepository.findAllByCorpCode(corpCodeValue);

                if (nowEntity.isEmpty()) {
                    // 최초 데이터 Insert
                    execOwnershipRepository.saveAll(execOwnershipEntityList);
                } else {
                    // 현재 DB 에서 가장 최근에 저장된 seq 넘버
                    final long nowLastRceptNo = nowEntity.stream()
                            .max(Comparator.comparing(ExecOwnershipEntity::getRceptNo))
                            .get()
                            .getRceptNo();

                    // 역순 정렬하여, 새로운 데이터 insert
                    Collections.reverse(execOwnershipEntityList);

                    List<ExecOwnershipEntity> newExecOwnershipEntityList = new ArrayList<>();

                    for (ExecOwnershipEntity execOwnershipEntity : execOwnershipEntityList) {
                        if (execOwnershipEntity.getRceptNo() == nowLastRceptNo) {
                            break;
                        } else {
                            newExecOwnershipEntityList.add(execOwnershipEntity);
                        }
                    }
                    execOwnershipRepository.saveAll(newExecOwnershipEntityList);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    public List<ExecOwnershipEntity> notifyInfo() {
        String nowDate = Date.nowTimeTo("yyyyMMdd");
        execOwnershipRepository.findByRegDtStartingWith("20230831");
        /**
         * [필요 정보]
         * 보고자 repror
         * 회사명 corp_name
         * 임원(등기여부) isu_exctv_ofcps
         * 임원 직위 isu_main_shrholdr
         * 발행 회사 관계 주요 주주 isu_main_shrholdr
         * 특정 증권 등 소유 수	sp_stock_lmp_cnt
         * 특정 증권 등 소유 증감 수 sp_stock_lmp_irds_cnt
         */
        return execOwnershipRepository.findByRegDtStartingWith("20230831");
    }

}
