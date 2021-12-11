<h1 align="center">
    Spark Star
</h1>

<h4 align="center">
    <a href="https://ispong.github.io/spark-star" >
        https://ispong.github.io/spark-star
    </a>
</h4>

<h4 align="center">
    🌟 Spark集成Hive的服务器插件，通过Restful快速查询hive中数据。
</h4>

### 📢 公告

目前，仅适用于**cdh-6.2.0**版本，支持**spark on yarn**模式和**local**模式。

### ✨ 模块

| 模块                                         | 状态                 | 说明                           |
|--------------------------------------------|--------------------|------------------------------|
| [star-common](./star-common/README.md)     | :white_check_mark: | 提供一系列常规工具类，包括StarTemplate组件。 |
| [star-plugin](./star-plugin/README.md)     | :white_check_mark: | 服务器插件本体。                     |
| [star-template](./star-template/README.md) | :white_check_mark: | 客户端使用插件的模板。                  |
| [demo1](./demo1/README.md)                 | :white_check_mark: | 案例：yarn模式执行sql。              |
| [demo2](./demo2/README.md)                 | :white_check_mark: | 案列：filter处理单条数据处理。           |

### 📦 安装使用

##### 插件安装(服务器端) 

```bash
git clone https://github.com/ispong/spark-star.git
cd star-plugin && mvn clean package
java -jar star-plugin.jar --server.port=${port} --star.config.private-key=${privateKey}
```

##### 插件使用(客户端)

```xml
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.star/star-common)](https://search.maven.org/artifact/com.isxcode.star/star-common)</version>
</dependency>
```

```java
@RestController
@RequestMapping
public class TemplateController {
    
    @GetMapping("/demo")
    public StarResponse testExecuteSpark() {
        
        // 初始化starTemplate
        StarTemplate starTemplate = StarFactory.build("${host}", "${port}", "${privateKey}");
        
        // 提交sql
        StarResponse = starTemplate.executeSql("select * from demo limit 100");
        
        // 等待，消费kafka状态
        xx = starTemplate.waitting(); 
        
        // 返回kafka结果
        return xx;
    }
}
```

### 👏 社区开发

> 欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
> 如需加入我们，请联系邮箱 ispong@outlook.com 。