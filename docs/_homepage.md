<p align="center">
  <a href="https://github.com/ispong/spark-star" style="border-bottom: none !important;">
    <img alt="spark-star" width="180" src="https://github.com/ispong/spark-star/raw/main/docs/assets/images/logo.png">
  </a>
</p>

<h1 align="center">
    Spark Star
</h1>

<h3 align="center">
    小黑星
</h3>

<h4 align="center">
    <a href="https://ispong.github.io/spark-star" >
        https://ispong.github.io/spark-star
    </a>
</h4>

<h4 align="center">
    🌟 Spark集成Hive的服务器插件，通过Restful快速查询hive中数据。
</h4>

### 📢 公告

目前，插件主要针对`Spark-3.1.4`版本进行开发。

### ✨ 模块

| 模块名                                          | 说明                                          |
|:---------------------------------------------| :-------------------------------------------- |
| [acorn-common](./acorn-common/README.md)     | 提供AcornTemplate组件，方便用户调用插件服务。 |
| [acorn-plugin](./acorn-plugin/README.md)     | 服务器插件本体。                              |
| [acorn-template](./acorn-template/README.md) | 如何使用插件的模板。                          |
| [demo1](./demo1/README.md)                   | kafka输入，kafka输出。                        |
| [demo2](./demo2/README.md)                   | kafka输入，mysql输出。                        |
| [demo3](./demo3/README.md)                   | kafka输入，hive输出。                         |

### 📦 安装使用

##### 服务器端，插件安装

```bash
# 或者 git clone https://gitee.com/ispong/spark-star.git
git clone https://github.com/ispong/spark-star.git
# 构建插件
cd acorn-plugin && mvn clean package
# 运行插件，默认端口`30155`
nohup java -jar -Xmx2048m ./target/acorn-plugin.jar >> ./spark-star.log 2>&1 &
```

##### 客户端，插件使用

```xml
<!-- 添加maven依赖 -->
<dependency>
    <groupId>com.isxcode.acorn</groupId>
    <artifactId>acorn-common</artifactId>
    <version>0.0.3-SNAPSHOT</version>
</dependency>
```

```java
@RestController
@RequestMapping
public class TemplateController {

    private final AcornTemplate acornTemplate;

    public TemplateApplication(AcornTemplate acornTemplate) {
        this.acornTemplate = acornTemplate;
    }

    @GetMapping("/demo")
    public AcornResponse testExecuteSpark() {

        // 输入点
        List<SparkCol> kafkaInputColumns = new ArrayList<>();
        kafkaInputColumns.add(new SparkCol("username", SparkSqlType.STRING));
        kafkaInputColumns.add(new SparkCol("age", SparkSqlType.INT));

        KafkaInput kafkaInput = KafkaInput.builder()
                .brokerList("host:port")
                .zookeeper("host:port")
                .topic("topicName")
                .dataFormat(DataFormat.CSV)
                .columnList(kafkaInputColumns)
                .build();

        // 输出点
        List<SparkCol> mysqlOutputColumns = new ArrayList<>();
        mysqlOutputColumns.add(new SparkCol("username", SparkSqlType.STRING));
        mysqlOutputColumns.add(new SparkCol("age", SparkSqlType.INT));

        MysqlOutput mysqlOutput = MysqlOutput.builder()
                .url("jdbc:mysql://host:port/dbName")
                .tableName("tableName")
                .driver("com.mysql.cj.jdbc.Driver")
                .username("username")
                .password("password")
                .columnList(mysqlOutputColumns)
                .build();

        // 构建请求对象
        ExecuteConfig executeConfig = ExecuteConfig.builder()
                .executeId("executeId")
                .flowId("flowId")
                .workType(WorkType.KAFKA_INPUT_MYSQL_OUTPUT)
                .kafkaInput(kafkaInput)
                .mysqlOutput(mysqlOutput)
                .build();

        // 运行
        return acornTemplate.executeSpark("host", "port", "key", executeConfig);
    }
}
```

### 👏 社区开发

> 欢迎大家一同维护开发，请参照具体[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
> 如需加入我们，请联系邮箱 ispong@outlook.com 。