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
