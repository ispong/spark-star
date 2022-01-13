package com.isxcode.star.plugin.controller;

import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.response.SuccessResponse;
import com.isxcode.star.plugin.service.StarBizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StarController {

    private final StarBizService starBizService;

    public StarController(StarBizService starBizService) {

        this.starBizService = starBizService;
    }

    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_SQL_URL)
    public StarData executeSql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSyncWork(starRequest, UrlConstants.EXECUTE_SQL_URL);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.GET_JOB_LOG_URL)
    public StarData getJobLog(@RequestBody StarRequest starRequest) {

        return starBizService.getJobLog(starRequest);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.STOP_JOB_URL)
    public StarData stopJob(@RequestBody StarRequest starRequest) {

        return starBizService.stopJob(starRequest);
    }

}
