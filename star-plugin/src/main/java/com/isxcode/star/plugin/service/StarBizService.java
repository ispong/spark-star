package com.isxcode.star.plugin.service;

import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.exception.StarException;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarBizService {


    private final StarService starService;

    private final StarSyncService starSyncService;

    public StarBizService(StarService starService,
                          StarSyncService starSyncService) {

        this.starService = starService;
        this.starSyncService = starSyncService;
    }

    public StarData executeSql(StarRequest starRequest) {

        if (starRequest.getSql() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        return starService.querySql(starRequest.getSql());
    }

    public StarData executeSyncWork(StarRequest starRequest, String url) {

        starSyncService.executeSyncWork(starRequest, url);

        return StarData.builder().build();
    }

}
