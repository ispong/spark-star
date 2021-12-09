package com.isxcode.star.template;

import com.isxcode.star.common.pojo.entity.ExecuteConfig;
import com.isxcode.star.common.pojo.entity.StarResponse;
import com.isxcode.star.common.service.StarTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@SpringBootApplication
public class TemplateApplication {

    private final StarTemplate starTemplate;

    public TemplateApplication(StarTemplate starTemplate) {
        this.starTemplate = starTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

    @GetMapping("/demo")
    public StarResponse demo() {

        ExecuteConfig executeConfig = ExecuteConfig.builder()
                .hasReturn(true)
                .sql("select * from rd_dev.ispong_table")
                .build();

        return starTemplate.executeSql("39.103.230.188", "30156", "key", executeConfig);
    }
}