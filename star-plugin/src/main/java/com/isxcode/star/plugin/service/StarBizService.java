package com.isxcode.star.plugin.service;

import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarBizService {

    private final StarSyncService starSyncService;

    public StarBizService(StarSyncService starSyncService) {
        
        this.starSyncService = starSyncService;
    }

    public StarData executeSyncWork(StarRequest starRequest, String url) {

        starSyncService.executeSyncWork(starRequest, url);

        return StarData.builder().build();
    }

}
