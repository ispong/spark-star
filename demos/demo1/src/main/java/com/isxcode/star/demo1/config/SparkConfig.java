package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class SparkConfig {

    private final DemoProperties demoProperties;

    public SparkConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    public void initConfigFile() {

        List<String> siteFileList = new ArrayList<>();
        siteFileList.add("core-site.xml");
        siteFileList.add("hdfs-site.xml");
        siteFileList.add("mapred-site.xml");
        siteFileList.add("yarn-site.xml");

        // 读取外部文件
        siteFileList.forEach(e -> {

            // 获取服务器上的配置文件
//                Path realPath = Paths.get(demoProperties.getHadoopConfigPath() + e);
//                UrlResource urlResource = new UrlResource(realPath.toUri());
//                String content = new BufferedReader(new InputStreamReader(urlResource.getInputStream())).lines().collect(Collectors.joining("\n"));
            // 写入项目中
            File file = new File("/home/dehoop/spark-star/demos/demo1/target/demo1-0.0.1.jar!/BOOT-INF/classes!/core-site.xml");
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPath)));
//                bufferedWriter.write(content);
//                bufferedWriter.flush();
//                bufferedWriter.close();
            // 读取系统文件
            try {
                System.out.println(e + " file ===" + new BufferedReader(new InputStreamReader(new FileInputStream(file))).lines().collect(Collectors.joining("\n")));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            };
        });
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
