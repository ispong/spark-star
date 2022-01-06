package com.isxcode.star.template.controller;

import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            .sql("select * from rd_dev.ispong_table limit 10")
            .build();

        StarResponse starResponse = starTemplate.build().executeSql(starRequest);
        System.out.println(starResponse.toString());
    }
}
