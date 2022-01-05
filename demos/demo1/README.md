#### demo 说明

> 此demo用于spark on yarn模式，测试多个count() 函数不可重复使用的问题

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

##### 运行

```bash
git clone https://gitee.com/ispong/spark-star.git
cd spark-star/demos/demo1
mvn clean package

# 通过spark-submit提交spring项目 
sudo cp ./target/demo1-0.0.1.jar /data/cdh/cloudera/parcels/CDH/lib/spark/work/demo1-0.0.1.jar
spark-submit --master yarn --class com.isxcode.star.demo1.Demo1Application --executor-memory=4G --num-executors 2 target/demo1-0.0.1.jar
```
