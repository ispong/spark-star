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

适用于spark on yarn模式，且目前版本支持`2.4.0-scala-2.11`版本Spark与`2.1.1`版本Hive

### ✨ 模块

| 模块名                                        | 说明                           |
|:-------------------------------------------|:-----------------------------|
| [star-common](./star-common/README.md)     | 提供StarTemplate组件，方便用户调用插件服务。 |
| [star-plugin](./star-plugin/README.md)     | 服务器插件本体。                     |
| [star-template](./star-template/README.md) | 如何使用插件的模板。                   |
| [demo1](./demo1/README.md)                 | 案例：通过sql查询hive上的数据           |
| [demo2](./demo2/README.md)                 | 案列：spark实现单条数据处理             |

### 📦 安装使用

##### 服务器端，插件安装

```bash
# 或者 git clone https://gitee.com/ispong/spark-star.git
git clone https://github.com/ispong/spark-star.git
# 构建插件
cd star-plugin && mvn clean package
# 运行插件，默认端口`30155`
nohup java -jar -Xmx2048m ./target/star-plugin.jar >> ./spark-star.log 2>&1 &
```

##### 客户端，插件使用

```xml
<!-- 添加maven依赖 -->
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>0.0.3-SNAPSHOT</version>
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

        // 运行
        return starTemplate.executeSpark("host", "port", "key", executeConfig);
    }
}
```

### 👏 社区开发

> 欢迎大家一同维护开发，请参照具体[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
> 如需加入我们，请联系邮箱 ispong@outlook.com 。