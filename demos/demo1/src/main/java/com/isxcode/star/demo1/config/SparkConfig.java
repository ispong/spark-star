package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.IOUtils;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.io.*;
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

        try {
            File file = new ClassPathResource("core-site.xml").getFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bufferedWriter.write("我特么烦死啦");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
