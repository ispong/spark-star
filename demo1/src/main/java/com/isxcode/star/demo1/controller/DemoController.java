package com.isxcode.star.demo1.controller;

import com.isxcode.star.demo1.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    public String demo() {

        String sql = "select * from rd_dev.ispong_table limit 10";
        demoService.executeSql(sql);

        return "hello world";
    }
}
