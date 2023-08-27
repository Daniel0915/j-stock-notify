package com.notify.service;

import com.notify.entity.LargeHoldingsEntity;
import com.notify.entity.dto.LargeHoldings.LHResponseDTO;
import com.notify.repository.LargeHoldingsRepository;
import com.notify.service.webclient.WebClientLargeHoldingServiceImpl;
import com.notify.service.webclient.WebClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

    public void insertData() {
        List<LargeHoldingsEntity> largeHoldingsEntityList = (List<LargeHoldingsEntity>) webClientLargeHoldingServiceImpl.get(baseUri, path, corpCodeKey, corpCodeValue);
        largeHoldingsRepository.saveAll(largeHoldingsEntityList);
    }



}
