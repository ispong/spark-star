package com.isxcode.star.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarRequest {

    private String executeId;

    private boolean hasReturn;

    private String sql;

    private List<String> sqls;

    private List<String> columns;

    private String database;

    private Integer page;

    private Integer pageSize;

    private String tableName;

    private Integer limit = 100;

    private String appId;
}
