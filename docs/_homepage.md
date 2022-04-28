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

> 目前支持`2.4.0(cdh-6.2.0)`版本，其他版本尚未支持，项目仅供参考。

### 📦 服务端安装说明

```bash
git clone https://github.com/ispong/spark-star.git
cd spark-star
nohup bash build.sh >> build.log 2>&1 &
#tail -f build.log
```

##### 启动插件

```bash
./bin/spark-star start
```

### 🔨 客户端使用说明

[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.star/star-common)](https://search.maven.org/artifact/com.isxcode.star/star-common)

```xml
<dependency>
    <groupId>com.isxcode.star</groupId>
    <artifactId>star-common</artifactId>
    <version>0.0.1</version>
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
                .db("dev")
                .sql("select * from demo_table")
                .build();

        StarResponse starResponse = starTemplate.build("work1").execute(starRequest);
        System.out.println(starResponse.toString());
    }
}
```

### 👏 社区开发

欢迎一同维护开发，具体请参照[开发文档](https://github.com/ispong/spark-star/blob/main/CONTRIBUTING.md) 。
如需加入我们，请联系邮箱 `ispong@outlook.com` 。
