<h1 align="center">
    Spark Star
</h1>

<h4 align="center">
    通过`Spring`插件的形式，实现对不同服务器与不同版本的`spark`做统一管理。
</h4>

<h4 align="center">
    ✨✨✨ <a href="https://ispong.github.io/spark-star">https://ispong.github.io/spark-star</a> ✨✨✨
</h4>

### 📢 公告

| 版本号              |  Spark Standalone  | Mesos |        YARN        | Kubernetes |
|------------------|:------------------:|:-----:|:------------------:|:----------:|
| 2.4.0(cdh-6.2.0) | :white_check_mark: |  :x:  | :white_check_mark: |    :x:     |

### 📒 文档

- [快速使用](https://ispong.github.io/flink-acorn/#/zh-cn/quickstart)

### 📦 使用说明

[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.star/star-common)](https://search.maven.org/artifact/com.isxcode.star/star-common)

```xml
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>0.0.1</version>
</dependency>
```

```yaml
star:
  node:
    host: 127.0.0.1
    port: 30156
    key: star-key
    kafka-config:
      topic: spark-star-topic
      bootstrap.servers: 127.0.0.1:9192
      group.id: test-consumer-group
```

```java
public class Demo{
    
    public void demo(){

        StarRequest starRequest = StarRequest.builder()
            .executeId("1234567890")
            .database("dev")
            .sql("select * from demo_table")
            .limit(100)
            .page(1)
            .pageSize(10)
            .key("name")
            .build();

        StarResponse starResponse = starTemplate.build().execute(starRequest);
        System.out.println(starResponse.toString());
    }
}
```

### 👏 社区开发

欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
如需加入我们，请联系邮箱 `ispong@outlook.com` 。
