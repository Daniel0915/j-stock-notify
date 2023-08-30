package com.notify.service;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.entity.LargeHoldingsEntity;
import com.notify.repository.LargeHoldingsRepository;
import com.notify.service.webclient.WebClientService;
import com.notify.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LargeHoldingsService {
    private final WebClientService webClientLargeHoldingServiceImpl;
    private final String baseUri;
    private final String path;
    private final String corpCodeKey;
    private final String corpCodeValue;
    private final LargeHoldingsRepository largeHoldingsRepository;

    @Autowired
    public LargeHoldingsService(@Value("${uri}") String baseUri, @Value("${uri.largeHoldings}") String path,
                                @Value("${corp.code.key}") String corpCodeKey, @Value("${corp.code.value}") String corpCodeValue, WebClientService webClientLargeHoldingServiceImpl,
                                LargeHoldingsRepository largeHoldingsRepository) {
        this.baseUri = baseUri;
        this.path = path;
        this.corpCodeKey = corpCodeKey;
        this.corpCodeValue = corpCodeValue;
        this.webClientLargeHoldingServiceImpl = webClientLargeHoldingServiceImpl;
        this.largeHoldingsRepository = largeHoldingsRepository;
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void insertData() {
        List<LargeHoldingsEntity> largeHoldingsEntityList = (List<LargeHoldingsEntity>) webClientLargeHoldingServiceImpl.get(baseUri, path, corpCodeKey, corpCodeValue);

        if (!largeHoldingsEntityList.isEmpty()) {
            final int lastIndex = largeHoldingsEntityList.size() - 1;

            final long lastRceptNo = largeHoldingsEntityList.get(lastIndex).getRceptNo();
            if (!largeHoldingsRepository.findByRceptNo(lastRceptNo).isPresent()) {

                // 현재 DB 에서 가장 최근에 저장된 seq 넘버
                final long nowLastRceptNo = largeHoldingsRepository.findAllByCorpCode(corpCodeValue).stream()
                                            .max(Comparator.comparing(LargeHoldingsEntity::getRceptNo))
                                            .get()
                                            .getRceptNo();

                // 역순 정렬하여, 새로운 데이터 insert
                Collections.reverse(largeHoldingsEntityList);

                List<LargeHoldingsEntity> newLargeHoldingsEntityList = new ArrayList<>();

                for (LargeHoldingsEntity largeHoldingsEntity : largeHoldingsEntityList) {
                    if (largeHoldingsEntity.getRceptNo() == nowLastRceptNo) {
                        break;
                    } else {
                        newLargeHoldingsEntityList.add(largeHoldingsEntity);
                    }
                }
                largeHoldingsRepository.saveAll(newLargeHoldingsEntityList);
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    public List<LargeHoldingsEntity> notifyInfo() {
        String nowDate = Date.nowTimeTo("yyyyMMdd");
        largeHoldingsRepository.findByRegDtStartingWith("20230831");
        /**
         * [필요 정보]
         * 보고자 repror
         * 회사명 corp_name
         * 보유주식등의 수 stkqy
         * 보유주식등의 증감 stkqy_irds
         * 보고사유 report_resn
         *
         */
        return largeHoldingsRepository.findByRegDtStartingWith("20230831");
    }


}
