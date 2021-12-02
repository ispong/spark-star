package com.isxcode.star.plugin.service;

import com.isxcode.star.common.dto.ExecuteSqlDto;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import static com.isxcode.star.common.menu.SparkType.NEW_SPARK;

@Service
public class StarBizService {

    private final SparkSession sparkSession;

    private final StarService starService;

    public StarBizService(SparkSession sparkSession,
                          StarService starService) {

        this.sparkSession = sparkSession;
        this.starService = starService;
    }

    public void executeSql(ExecuteSqlDto executeSqlDto) {

        SparkSession executeSqlSession = sparkSession;
        if (NEW_SPARK.equals(executeSqlDto.getSparkType())) {
            executeSqlSession = starService.generateNewSession();
        }

        Dataset<Row> rowDataset = executeSqlSession.sql(executeSqlDto.getSql());
        rowDataset.show();
    }

}
