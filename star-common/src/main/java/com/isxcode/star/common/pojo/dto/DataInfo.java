package com.isxcode.star.common.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataInfo {

    private List<String> columnNames;

    private List<List<String>> dataList;
}
