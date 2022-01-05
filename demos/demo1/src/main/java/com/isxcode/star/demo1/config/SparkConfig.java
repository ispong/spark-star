package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.IOUtils;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class SparkConfig {

    private final DemoProperties demoProperties;

    public SparkConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    public void initConfigFile() {

        // 读取外部文件
        demoProperties.getSiteXmlPaths().forEach(e -> {
            Path path = Paths.get(e);
            try {
                UrlResource urlResource = new UrlResource(path.toUri());
                System.out.println("getSiteXmlPaths:" + new BufferedReader(new InputStreamReader(urlResource.getInputStream())).lines().collect(Collectors.joining("\n")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // 写入到项目中
        InputStream xxx = getClass().getClassLoader().getResourceAsStream("core-site.xml");
        assert xxx != null;
        System.out.println("initConfigFile:" + new BufferedReader(new InputStreamReader(xxx)).lines().collect(Collectors.joining("\n")));
    }

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        // 初始化配置文件进入spring的项目流中
        initConfigFile();

        log.info("初始化sparkSession");
        SparkSession.Builder sparkBuilder = SparkSession
                .builder()
                .appName(demoProperties.getAppName())
                .master(demoProperties.getMaster());

        demoProperties.getSparkConfig().forEach(sparkBuilder::config);

        return sparkBuilder.enableHiveSupport().getOrCreate();
    }
}
