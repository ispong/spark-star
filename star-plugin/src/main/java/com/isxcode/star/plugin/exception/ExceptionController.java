package com.isxcode.star.plugin.exception;

import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.response.StarResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    /**
     * key为null
     */
    @RequestMapping("/keyIsNull")
    public StarResponse keyIsNull() {

        return new StarResponse(StarExceptionEnum.KEY_IS_NULL);
    }

    /**
     * key错误
     */
    @RequestMapping("/keyIsError")
    public StarResponse keyIsError() {

        return new StarResponse(StarExceptionEnum.KEY_IS_ERROR);
    }
}
