package com.isxcode.star.plugin.service.impl;

import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.exception.StarException;
import com.isxcode.star.plugin.service.SqlParseService;
import com.isxcode.star.plugin.service.StarService;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {

    private final SparkSession sparkSession;

    private final SqlParseService sqlParseService;

    @Override
    public StarData queryData(StarRequest starRequest) {

        // 校验关键字
        Assert.notNull(starRequest.getSql(), "sql字段不能为空");

        // 解析sql,判断sql中是否存在`limit`字段
        boolean hasLimit = sqlParseService.hasLimit(starRequest.getSql());
        if (!hasLimit) {
            if (starRequest.getLimit() == null) {
                throw new StarException("查询必须要有条数限制");
            } else {
                starRequest.setSql(starRequest.getSql() + " limit " + starRequest.getLimit());
            }
        }

        // 执行sql
        Dataset<Row> rowDataset = sparkSession.sql(starRequest.getSql());

        // 初始化返回对象
        StarData.StarDataBuilder starDataBuilder = StarData.builder();

        // 获取字段列名
        String[] columns = rowDataset.columns();
        starDataBuilder.columnNames(Arrays.asList(columns));

        // 获取数据值
        List<List<String>> dataList = new ArrayList<>();
        List<Row> rows = rowDataset.collectAsList();
        rows.forEach(e -> {
            List<String> metaData = new ArrayList<>();
            for (int i = 0; i < e.size(); i++) {
                metaData.add(String.valueOf(e.get(i)));
            }
            dataList.add(metaData);
        });

        // 返回结果
        return starDataBuilder.dataList(dataList).build();
    }
}
