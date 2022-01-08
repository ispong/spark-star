package com.isxcode.star.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarRequest {

    private String executeId;

    private boolean hasReturn;

    private String sql;

    private String database;

    private Integer page;

    private Integer pageSize;

    private String tableName;

    private Integer limit;
}
