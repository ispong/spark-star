package com.isxcode.star.plugin.response;

import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

@Aspect
@Slf4j
public class SuccessResponseAdvice {

    private final MessageSource messageSource;

    public SuccessResponseAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Pointcut("@annotation(com.isxcode.star.plugin.response.SuccessResponse)")
    public void operateLog() {
    }

    @AfterReturning(returning = "data", value = "operateLog()&&@annotation(successResponse)")
    public void afterReturning(JoinPoint joinPoint, StarData data, SuccessResponse successResponse) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        StarResponse baseResponse = new StarResponse();
        if (!"void".equals(signature.getReturnType().getName())) {
            baseResponse.setCode("200");
            if (data.getClass().getDeclaredFields().length == 0) {
                baseResponse.setStarData(null);
            } else {
                baseResponse.setStarData(data);
            }
            baseResponse.setMessage(getMsg(successResponse));
            successResponse(baseResponse);
        } else {
            baseResponse.setCode("200");
            baseResponse.setMessage(getMsg(successResponse));
            successResponse(baseResponse);
        }

    }

    public String getMsg(SuccessResponse successResponse) {

        if (!successResponse.value().isEmpty()) {
            return successResponse.value();
        }

        try {
            return messageSource.getMessage(successResponse.msg(), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return successResponse.msg();
        }
    }

    public void successResponse(StarResponse baseResponse) {

        throw new SuccessException(baseResponse);
    }

}
