package com.isxcode.star.common.pojo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecuteConfig {

    private boolean hasReturn;

    private String sql;
}
