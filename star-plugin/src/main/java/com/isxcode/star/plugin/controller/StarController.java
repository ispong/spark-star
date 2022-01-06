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

    /**
     * 执行spark-sql
     */
    @SuccessResponse
    @PostMapping(UrlConstants.EXECUTE_SQL_URL)
    public StarData executeSql(@RequestBody StarRequest starRequest) {

        return starBizService.executeSql(starRequest);
    }
}
