package com.notify.service;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.repository.ExecOwnershipRepository;
import com.notify.service.webclient.WebClientLargeHoldingServiceImpl;
import com.notify.service.webclient.WebClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    public List<ExecOwnershipEntity> insertData() {
        List<ExecOwnershipEntity> execOwnershipEntityList = (List<ExecOwnershipEntity>) webClientExecOwnershipServiceImpl.get(baseUri, path, corpCodeKey, corpCodeValue);

        execOwnershipRepository.saveAll(execOwnershipEntityList);
        return execOwnershipEntityList;


    }
}
