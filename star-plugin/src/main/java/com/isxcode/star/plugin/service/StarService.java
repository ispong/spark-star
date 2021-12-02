package com.isxcode.star.plugin.service;

import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public interface StarService {

    /**
     * 初始化全新的session
     */
    SparkSession generateNewSession();

}
