package com.isxcode.star.plugin.service;

import com.isxcode.star.common.menu.ResponseEnum;
import com.isxcode.star.common.pojo.dto.DataInfo;
import com.isxcode.star.common.pojo.entity.ExecuteConfig;
import com.isxcode.star.common.pojo.entity.StarResponse;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarBizService {

    private final SparkSession sparkSession;

    public StarBizService(SparkSession sparkSession) {

        this.sparkSession = sparkSession;
    }

    public StarResponse executeSql(ExecuteConfig executeConfig) {

        StarResponse starResponse = new StarResponse();

        // 执行sql
        Dataset<Row> rowDataset = sparkSession.sql(executeConfig.getSql());

        if (!executeConfig.isHasReturn()) {
            return new StarResponse(ResponseEnum.EXECUTE_SUCCESS);
        }

        // 解析结果表
        DataInfo dataInfo = new DataInfo();

        // 添加表头
        String[] columns = rowDataset.columns();
        dataInfo.setColumnNames(Arrays.asList(columns));

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
        dataInfo.setDataList(dataList);

        starResponse.setDataInfo(dataInfo);
        return new StarResponse(ResponseEnum.EXECUTE_SUCCESS);
    }

}
