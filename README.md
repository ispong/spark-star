<p align="center">
  <a href="https://github.com/ispong/spark-star" style="border-bottom: none !important;">
    <img alt="spark-star" width="180" src="https://github.com/ispong/spark-star/raw/main/docs/assets/images/logo.png">
  </a>
</p>

<h1 align="center">
    Spark Star
</h1>

<h3 align="center">
    黑星
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

目前，仅适用于`cdh-6.2.0`版本，支持`spark on yarn`模式和`local`模式。

### ✨ 模块

| 模块名                                        | 说明                |
|:-------------------------------------------|:------------------|
| [star-common](./star-common/README.md)     | 提供StarTemplate组件。 |
| [star-plugin](./star-plugin/README.md)     | 服务器插件本体。          |
| [star-template](./star-template/README.md) | 使用插件模板。           |
| [demo1](./demo1/README.md)                 | 案例：yarn模式         |
| [demo2](./demo2/README.md)                 | 案列：spark单条数据处理    |

### 📦 安装使用

##### 插件安装(服务器端) 

```bash
git clone https://github.com/ispong/spark-star.git
cd star-plugin && mvn clean package
java -jar star-plugin.jar
```

##### 插件使用(客户端)

```xml
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>0.0.1</version>
</dependency>
```

```java
@RestController
@RequestMapping
public class TemplateController {

    private final StarTemplate starTemplate;

    public TemplateApplication(StarTemplate starTemplate) {
        this.starTemplate = starTemplate;
    }

    @GetMapping("/demo")
    public StarResponse testExecuteSpark() {
        
        return starTemplate.executeSql("${host}", "${port}", "${secretKey}", executeConfig);
    }
}
```

### 👏 社区开发

> 欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
> 如需加入我们，请联系邮箱 ispong@outlook.com 。