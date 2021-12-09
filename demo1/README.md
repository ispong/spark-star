### 前提

> Note: 针对的版本

```xml
<properties>
    <spark.version>2.4.0-cdh6.2.0</spark.version>
    <scala.version>2.11</scala.version>
    <hive.version>2.1.1-cdh6.2.0</hive.version>
    <hadoop.version>3.0.0-cdh6.2.0</hadoop.version>
    <janino.version>3.0.8</janino.version>
</properties>
```

> Note:
> 在spring项目中运行yarn模式，无法读取core-site.xml、yarn-site.xml、mapred-site.xml、hdfs-site.xml这四个文件

```bash
sudo vim /etc/profile

# === sudo vim /etc/profile ===
export HADOOP_HOME=/data/cdh/cloudera/parcels/CDH/lib/hadoop
export SPARK_HOME=/data/cdh/cloudera/parcels/CDH/lib/spark
# === sudo vim /etc/profile ===

source /etc/profile
```

```http request
GET http://localhost:30156/demo
```