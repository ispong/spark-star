<h1 align="center">
    Spark Star
</h1>

<h3 align="center">
    🌟 Spark集成Hive的服务器插件，通过Restful快速查询hive中数据。
</h3>

<h4 align="center">
    ✨✨✨ <a href="https://ispong.github.io/spark-star" >
        https://ispong.github.io/spark-star
    </a> ✨✨✨
</h4>

### 📢 公告

目前，仅支持**cdh-6.2.0**版本的spark模块，支持**spark on yarn**模式和**local**模式。

### ✨ 模块说明

| 模块                                                   | 状态                 | 说明                           |
|------------------------------------------------------|--------------------|------------------------------|
| [star-common](https://ispong.github.io/spark-star)   | :white_check_mark: | 提供一系列常规工具类，包括StarTemplate组件。 |
| [star-plugin](https://ispong.github.io/spark-star)   | :white_check_mark: | 服务器插件本体。                     |
| [star-template](https://ispong.github.io/spark-star) | :white_check_mark: | 客户端使用插件的模板。                  |
| [demos](https://ispong.github.io/spark-star)         | :white_check_mark: | 各种spark相关的demo。              |

### 📒 相关文档

- [快速使用入口](https://ispong.github.io/flink-acorn/#/zh-cn/start/%E5%BF%AB%E9%80%9F%E4%BD%BF%E7%94%A8)
- [开发者手册](https://ispong.github.io/flink-acorn/#/zh-cn/contributing)
- [版本历史](https://ispong.github.io/flink-acorn/#/zh-cn/changelog)

### 📦 安装使用

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
    host: xxx.xxx.xxx.xxx
    port: 30156
    key: star-key
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

### 👏 社区开发

欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
如需加入我们，请联系邮箱 ispong@outlook.com 。
