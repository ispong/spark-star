package com.isxcode.star.plugin.response;

import com.isxcode.star.common.constant.MsgConstants;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SuccessResponse {

    /**
     * default msg
     *
     * @return default msg
     */
    String value() default MsgConstants.SUCCESS_RESPONSE_MSG;

    /**
     * msg
     *
     * @return msg
     */
    String msg() default MsgConstants.SUCCESS_RESPONSE_MSG;
}
