package com.isxcode.star.plugin.service;

import com.isxcode.star.common.dto.ExecuteSqlDto;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

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

        SparkSession sparkSession1 = sparkSession.newSession();
        SparkSession sparkSession2 = this.sparkSession.newSession();
        sparkSession2.close();
        Dataset<Row> rowDataset = sparkSession1.sql(executeSqlDto.getSql());
        rowDataset.show();
        sparkSession1.close();
    }

}
