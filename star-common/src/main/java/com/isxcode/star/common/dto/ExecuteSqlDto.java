package com.isxcode.star.common.dto;

import com.isxcode.star.common.menu.SparkType;
import lombok.Data;

@Data
public class ExecuteSqlDto {

    private String sql;

    private String executeId;

    private SparkType sparkType;
}
