package com.isxcode.star;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.response.StarRequest;
import org.apache.spark.sql.SparkSession;

public class Execute {

    public static void main(String[] args) {

        // 获取请求参数
        StarRequest starRequest = JSON.parseObject(String.valueOf(args[0]), StarRequest.class);

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder().getOrCreate();

        // 执行spark
        sparkSession.sql("use " + starRequest.getDatabase());
        sparkSession.sql(starRequest.getSql());

        // 停止session
        sparkSession.stop();
    }
}
