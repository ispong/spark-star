package com.isxcode.star.plugin.controller;

import com.isxcode.star.common.menu.ResponseEnum;
import com.isxcode.star.common.pojo.entity.StarResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    /**
     * token为null异常
     */
    @RequestMapping("/tokenIsNull")
    public StarResponse tokenIsNull() {

        return new StarResponse(ResponseEnum.REMOTE_ERROR);
    }

    /**
     * token不合法异常
     */
    @RequestMapping("/tokenIsInvalid")
    public StarResponse tokenIsInvalid() {

        return new StarResponse(ResponseEnum.REMOTE_ERROR);
    }

    /**
     * 权限不足异常
     */
    @RequestMapping("/exception/authError")
    public StarResponse exceptionAuthError() {

        return new StarResponse(ResponseEnum.REMOTE_ERROR);
    }
}
