<h1 align="center">
    Spark Star
</h1>

<h4 align="center">
    通过Spring插件的形式，实现对不同服务器与不同版本的Spark做统一管理。
</h4>

<h4 align="center">
    ✨✨✨ <a href="https://spark-star.isxcode.com">https://spark-star.isxcode.com</a> ✨✨✨
</h4>

### 📢 公告

> 目前支持`3.1.1(apache)`版本，其他版本尚未支持，项目仅供参考。
 
### 📒 文档

- [快速使用](https://spark-star.isxcode.com/#/zh-cn/quickstart)

### 📦 使用说明

[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.star/star-common)](https://search.maven.org/artifact/com.isxcode.star/star-common)

```xml
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

```yaml
spark-star:
  workers:
    work1:
      host: 127.0.0.1
      port: 30157
      key: star-key
```

```java
public class Demo{
    public void demo(){
        
        StarRequest starRequest = StarRequest.builder()
                .sql("select * from dev.demo_table")
                .build();

        StarResponse starResponse = starTemplate.build("work1").execute(starRequest);
        
        System.out.println(starResponse.toString());
    }
}
```

### 👏 社区开发

欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
如需加入我们，请联系邮箱 `ispong@outlook.com` 。
