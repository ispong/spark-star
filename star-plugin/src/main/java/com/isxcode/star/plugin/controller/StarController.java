package com.isxcode.star.plugin.controller;

import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.response.SuccessResponse;
import com.isxcode.star.plugin.service.StarBizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 插件api入口
 *
 * @ispong
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class StarController {

    private final StarBizService starBizService;

    @SuccessResponse("查询成功")
    @PostMapping(UrlConstants.QUICK_EXECUTE_QUERY_URL)
    public StarData quickExecuteQuerySql(@RequestBody StarRequest starRequest) {

        return starBizService.quickExecuteQuerySql(starRequest);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_URL)
    public StarData executeSql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSyncWork(starRequest, UrlConstants.EXECUTE_URL);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_QUERY_URL)
    public StarData executeQuerySql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSyncWork(starRequest, UrlConstants.EXECUTE_QUERY_URL);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_PAGE_QUERY_URL)
    public StarData executePageQuerySql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSyncWork(starRequest, UrlConstants.EXECUTE_PAGE_QUERY_URL);
    }

    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_MULTI_SQL_URL)
    public StarData executeMultiSql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSyncWork(starRequest, UrlConstants.EXECUTE_MULTI_SQL_URL);
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
