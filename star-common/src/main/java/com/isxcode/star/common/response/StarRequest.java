package com.isxcode.star.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarRequest {

    private String executeId;

    private boolean hasReturn;

    private String sql;
}
