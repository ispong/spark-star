package com.isxcode.star.plugin.controller;


import com.isxcode.star.common.dto.ExecuteSqlDto;
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
     * 执行一个
     */
    @PostMapping("/executeSql")
    public void executeSql(@RequestBody ExecuteSqlDto executeSqlDto) {

        starBizService.executeSql(executeSqlDto);
    }
}
