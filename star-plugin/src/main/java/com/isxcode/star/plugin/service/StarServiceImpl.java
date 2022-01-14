package com.isxcode.star.plugin.service;

import com.isxcode.star.common.pojo.dto.StarData;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarServiceImpl implements StarService {

    private final SparkSession sparkSession;

    public StarServiceImpl(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    @Override
    public StarData queryData(String database, String tableName) {

        Dataset<Row> rowDataset = sparkSession.sql("select * from " + database + "." + tableName);

        String[] columns = rowDataset.columns();
        StarData.StarDataBuilder starDataBuilder = StarData.builder().columnNames(Arrays.asList(columns));

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
