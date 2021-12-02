##### 使用前提

> 设置环境变量 sparkhome

```log
\assembly\target\scala-2.11\jars' does not exist; make sure Spark is built.
```

```bash
sudo vim /etc/profile
# === sudo vim /etc/profile ===
export SPARK_HOME=/data/cdh/cloudera/parcels/CDH/lib/spark
# === sudo vim /etc/profile ===
source /etc/profile
```

##### 添加achive

// 配置spark.yarn.archive
// cd /data/cdh/cloudera/parcels/CDH/lib/spark/jars
// zip -q -r spark_jars.zip *
// hadoop fs -mkdir -p /spark-yarn/zip
// hadoop fs -put spark_jars.zip /spark-yarn/zip/