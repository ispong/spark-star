package com.isxcode.star.template.controller;

import com.isxcode.star.common.pojo.entity.StarRequest;
import com.isxcode.star.common.pojo.entity.StarResponse;
import com.isxcode.star.common.template.StarFactory;
import com.isxcode.star.common.template.StarTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DemoController {

    @GetMapping("/submitSql")
    public StarResponse submitSql(@RequestParam String sql) {

        StarTemplate starTemplate = StarFactory.build("node1");

        StarRequest starRequest = StarRequest.builder()
                .hasReturn(true)
                .sql(sql)
                .build();

        return starTemplate.executeSql(starRequest);
    }
}
