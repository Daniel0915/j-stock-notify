package com.notify.service.webclient;

import com.notify.entity.dto.ExecOwnership.EOResponseDTO;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientTelegramServiceImpl implements WebClientService {

    @Override
    public List<?> get(String baseUri, String path, String paraKey, String paraValue) {
        // http 상태 에러 시, 오류 이메일 전송(예외 처리)
        Mono<EOResponseDTO> eoResponseDTOMono  = WebClient.create(baseUri)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(path)
                        .queryParam(paraKey, paraValue)
                        .build())
                .retrieve()
                .bodyToMono(EOResponseDTO.class);
        return eoResponseDTOMono.block().toListExecOwnershipEntity();
    }
}
