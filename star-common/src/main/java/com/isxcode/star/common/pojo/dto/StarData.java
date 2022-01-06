package com.isxcode.star.common.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StarData {

    private List<String> columnNames;

    private List<List<String>> dataList;
}
