package com.isxcode.star.plugin.service;

import com.isxcode.star.common.dto.ExecuteSqlDto;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class StarBizService {

    private final StarService starService;

    public StarBizService(StarService starService) {

        this.starService = starService;
    }

    public void executeSql(ExecuteSqlDto executeSqlDto) {

        SparkSession executeSqlSession = starService.generateNewSession();
        SparkSession executeSqlSession1 = starService.generateNewSession();
        Dataset<Row> rowDataset = executeSqlSession.sql(executeSqlDto.getSql());
        rowDataset.show();
        executeSqlSession.close();
        Dataset<Row> sql = executeSqlSession1.sql(executeSqlDto.getSql());
        sql.show();
        executeSqlSession1.close();
    }

}
