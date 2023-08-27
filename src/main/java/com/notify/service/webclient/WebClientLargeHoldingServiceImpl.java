package com.notify.service.webclient;

import com.notify.entity.LargeHoldingsEntity;
import com.notify.entity.dto.LargeHoldings.LHResponseDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebClientLargeHoldingServiceImpl implements WebClientService {
    private final String dartKey;
    private final String dartValue;

    @Autowired
    public WebClientLargeHoldingServiceImpl(@Value("${dart.key}") String dartKey, @Value("${dart.value}") String dartValue) {
        this.dartKey = dartKey;
        this.dartValue = dartValue;
    }

    @Override
    public List<?> get(String baseUri, String path, String paraKey, String paraValue) {
        Mono<LHResponseDTO> lhResponseDtoMono = WebClient.create(baseUri)
                                                .get()
                                                .uri(uriBuilder -> uriBuilder
                                                .pathSegment(path)
                                                .queryParam(paraKey, paraValue)
                                                .queryParam(dartKey, dartValue)
                                                .build())
                                                .retrieve()
                                                .bodyToMono(LHResponseDTO.class);
        return lhResponseDtoMono.block().toListLargeHoldingsEntity();
    }

}
