package com.isxcode.star.common.pojo.entity;

import com.isxcode.star.common.menu.ResponseEnum;
import com.isxcode.star.common.pojo.dto.DataInfo;
import lombok.Data;

@Data
public class StarResponse {

    private ResponseEnum response;

    private DataInfo dataInfo;

    public StarResponse() {
    }

    public StarResponse(ResponseEnum responseEnum) {
        this.response = responseEnum;
    }
}
