package com.isxcode.star.plugin.service.impl;

import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.exception.StarException;
import com.isxcode.star.plugin.service.SqlParseService;
import com.isxcode.star.plugin.service.StarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {

    private final SparkSession sparkSession;

    private final SqlParseService sqlParseService;

    @Override
    public StarData queryData(StarRequest starRequest) {

        // 校验关键字
        Assert.notNull(starRequest.getDatabase(), "database不能为空");
        Assert.notNull(starRequest.getTableName(), "tableName不能为空");
        Assert.notEmpty(starRequest.getColumns(), "columns不能为空");

        // 拼接sql
        String querySql = " select " + Strings.join(starRequest.getColumns(), ',') + " from " + starRequest.getTableName();

        // 执行sql
        Dataset<Row> rowDataset;
        try {
            sparkSession.sql("use " + starRequest.getDatabase());
            rowDataset = sparkSession.sql(querySql).limit(starRequest.getLimit());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StarException(e.getMessage());
        }

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
