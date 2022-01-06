package com.isxcode.star.plugin.controller;


import com.isxcode.star.plugin.service.StarBizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spark-star")
public class StarController {

    private final StarBizService starBizService;

    public StarController(StarBizService starBizService) {

        this.starBizService = starBizService;
    }

    /**
     * 执行sparksql
     */
    @PostMapping("/executeSql")
    public StarResponse executeSql(@RequestBody StarRequest starRequest) {

        starBizService.executeSql(starRequest);

        return new StarResponse(ResponseEnum.SUBMIT_SUCCESS);
    }

    public void getWorkLog() {

    }

    public void getTableInfo() {

    }

    public void listTables() {

    }

    public void foreachParseData() {

    }

    public void getWorkStatus() {

    }
}
