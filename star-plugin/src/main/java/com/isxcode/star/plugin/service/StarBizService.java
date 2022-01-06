package com.isxcode.star.plugin.service;

import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.plugin.exception.StarException;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StarBizService {

    private final SparkSession sparkSession;

    public StarBizService(SparkSession sparkSession) {

        this.sparkSession = sparkSession;
    }

    public StarData executeSql(StarRequest starRequest) {

        // 关键字检测
        if (starRequest.getSql() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        // 执行sql
        Dataset<Row> rowDataset = sparkSession.sql(starRequest.getSql());

        // 添加表头
        String[] columns = rowDataset.columns();
        StarData.StarDataBuilder starDataBuilder = StarData.builder().columnNames(Arrays.asList(columns));

        // 添加数据
        List<List<String>> dataList = new ArrayList<>();
        List<Row> rows = rowDataset.collectAsList();
        rows.forEach(e -> {
            List<String> metaData = new ArrayList<>();
            for (int i = 0; i < e.size(); i++) {
                metaData.add(String.valueOf(e.get(i)));
            }
            dataList.add(metaData);
        });

        return starDataBuilder.dataList(dataList).build();
    }
}
