package com.isxcode.star.template.controller;

import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final StarTemplate starTemplate;

    public DemoController(StarTemplate starTemplate) {

        this.starTemplate = starTemplate;
    }

    @GetMapping("/executeSql")
    public void submitSql() {

        StarRequest starRequest = StarRequest.builder()
            .executeId("123")
            .sql("select * from rd_dev.ispong_table limit 10")
            .build();

        StarResponse starResponse = starTemplate.build().executeSql(starRequest);
        System.out.println(starResponse.toString());
    }

    @GetMapping("/getLog")
    public void getLog(@RequestParam String appId) {

        StarRequest starRequest = StarRequest.builder()
            .appId(appId)
            .build();

        StarResponse starResponse = starTemplate.build().getLog(starRequest);
        System.out.println(starResponse.toString());
    }

    @GetMapping("/stopJob")
    public void stopJob(@RequestParam String appId) {

        StarRequest starRequest = StarRequest.builder()
            .appId(appId)
            .build();

        StarResponse starResponse = starTemplate.build().stopJob(starRequest);
        System.out.println(starResponse.toString());
    }
}
