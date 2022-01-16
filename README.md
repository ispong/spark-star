<h1 align="center">
    Spark Star
</h1>

<h4 align="center">
    通过服务器插件的形式，实现对不同服务器与不同版本的spark做统一管理。
</h4>

<h4 align="center">
    ✨✨✨ <a href="https://ispong.github.io/spark-star">https://ispong.github.io/spark-star</a> ✨✨✨
</h4>

### 📢 公告

| 版本号          | Spark Standalone   | Mesos | YARN               | Kubernetes |
|--------------|--------------------|-------|--------------------|------------|
| 2.4.0(6.2.0) | :white_check_mark: | :x:   | :white_check_mark: | :x:        |

### 📒 文档

- [快速使用](https://ispong.github.io/flink-acorn/#/zh-cn/quickstart)
- [开发者手册](https://ispong.github.io/flink-acorn/#/zh-cn/contributing)
- [版本历史](https://ispong.github.io/flink-acorn/#/zh-cn/changelog)

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
    host: ${spark-server-host}
    port: ${star-plugin-port}
    key: ${star-plugin-key}
    kafka-config:
       bootstrap.servers: ${kafka-servers}
       group.id: ${kafka-group-id}
```

```java
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final StarTemplate starTemplate;

    public DemoController(StarTemplate starTemplate) {
        this.starTemplate = starTemplate;
    }

    @GetMapping("/executeSql")
    public void submitSql() {

        StarRequest starRequest = StarRequest.builder()
            .sql("select * from rd_dev.ispong_table limit 10")
            .build();

        StarResponse starResponse = starTemplate.build().executeSql(starRequest);
        System.out.println(starResponse.toString());
    }
}
```

```java

```

### 👏 社区开发

欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
如需加入我们，请联系邮箱 `ispong@outlook.com` 。
