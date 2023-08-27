package com.notify.service;

import com.notify.entity.dto.ExecOwnership.EOResponseDTO;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestTemplateService {
    public ResponseEntity<?> get(String targetUri) {
        URI uri = UriComponentsBuilder
                .fromUriString(targetUri)
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> result = restTemplate.getForEntity(uri, EOResponseDTO.class);



        System.out.println("--- client get method ---");
        System.out.println("status code : " + result.getStatusCode());
        System.out.println("body : " + result.getBody());
        return null;
    }

}
