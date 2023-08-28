package com.notify.service;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.entity.LargeHoldingsEntity;
import com.notify.repository.ExecOwnershipRepository;
import com.notify.service.webclient.WebClientService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
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
    public List<ExecOwnershipEntity> insertData() {
        List<ExecOwnershipEntity> execOwnershipEntityList = (List<ExecOwnershipEntity>) webClientExecOwnershipServiceImpl.get(baseUri, path, corpCodeKey, corpCodeValue);

        if (!execOwnershipEntityList.isEmpty()) {
            final int lastIndex = execOwnershipEntityList.size() - 1;

            final long lastRceptNo = execOwnershipEntityList.get(lastIndex).getRceptNo();

            if (!execOwnershipRepository.findByRceptNo(lastRceptNo).isPresent()) {

                // 현재 DB 에서 가장 최근에 저장된 seq 넘버
                final long nowLastRceptNo = execOwnershipRepository.findAllByCorpCode(corpCodeValue).stream()
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
        return execOwnershipEntityList;


    }
}
