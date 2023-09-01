package com.notify.service;

import com.notify.service.webclient.WebClientService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {
    private final static String chatInfoUpdatePath = "getUpdates";

    private final WebClientService webClientTelegramServiceImpl;
    private final String baseUri;

    @Autowired
    public TelegramService(WebClientService webClientTelegramServiceImpl, @Value("${telegram.base.uri}") String baseUri) {
        this.webClientTelegramServiceImpl = webClientTelegramServiceImpl;
        this.baseUri = baseUri;
    }

    public List<?> chatInfoUpdate() {
        return webClientTelegramServiceImpl.get(baseUri, chatInfoUpdatePath, "", "");

    }

}
