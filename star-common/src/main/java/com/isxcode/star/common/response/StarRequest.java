package com.isxcode.star.common.response;

import lombok.Data;

@Data
public class StarRequest {

    private String executeId;

    private boolean hasReturn;

    private String sql;
}
