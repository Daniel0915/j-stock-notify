package com.notify.service.webclient;


import com.notify.entity.dto.ExecOwnership.EOResponseDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebClientExecOwnershipServiceImpl implements WebClientService {
    private final String dartKey;
    private final String dartValue;

    @Autowired
    public WebClientExecOwnershipServiceImpl(@Value("${dart.key}") String dartKey, @Value("${dart.value}") String dartValue) {
        this.dartKey = dartKey;
        this.dartValue = dartValue;
    }

    @Override
    public List<?> get(String baseUri, String path, String paraKey, String paraValue) {
        Mono<EOResponseDTO> eoResponseDTOMono  = WebClient.create(baseUri)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(path)
                        .queryParam(paraKey, paraValue)
                        .queryParam(dartKey, dartValue)
                        .build())
                .retrieve()
                .bodyToMono(EOResponseDTO.class);
        return eoResponseDTOMono.block().toListExecOwnershipEntity();
    }
}
