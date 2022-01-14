package com.isxcode.star.template.controller;

import com.isxcode.star.template.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/execute")
    public String execute() {

        templateService.execute();

        return "运行成功等待结果";
    }

    @GetMapping("/executeQuery")
    public String executeQuery() {

        templateService.executeQuery();

        return "运行成功等待结果";
    }

    @GetMapping("/executePageQuery")
    public String executePageQuery() {

        templateService.executePageQuery();

        return "运行成功等待结果";
    }

    @GetMapping("/executeMultiSql")
    public String executeMultiSql() {

        templateService.executeMultiSql();

        return "运行成功等待结果";
    }

//    @GetMapping("/getLog")
//    public String getLog(@RequestParam String appId) {
//
//        StarRequest starRequest = StarRequest.builder()
//            .appId(appId)
//            .build();
//
//        StarResponse starResponse = starTemplate.build().getLog(starRequest);
//        return starResponse.toString();
//    }
//
//    @GetMapping("/stopJob")
//    public String stopJob(@RequestParam String appId) {
//
//        StarRequest starRequest = StarRequest.builder()
//            .appId(appId)
//            .build();
//
//        StarResponse starResponse = starTemplate.build().stopJob(starRequest);
//        return starResponse.toString();
//    }

}
