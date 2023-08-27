package com.notify.service.webclient;

import com.notify.entity.LargeHoldingsEntity;
import java.util.List;

public interface WebClientService {
    List<?> get(String baseUri, String path, String paraKey, String paraValue);


}
