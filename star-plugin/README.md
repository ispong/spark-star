##### 使用前提

> 设置环境变量 sparkhome

#### 添加HADOOP_CONF_DIR到spark-envs.sh


```bash
sudo vim /etc/profile
export HADOOP_HOME=/data/cdh/cloudera/parcels/CDH/lib/hadoop
export SPARK_HOME=/data/cdh/cloudera/parcels/CDH/lib/spark
export SPARK_CONF_DIR=/data/cdh/cloudera/parcels/CDH/lib/spark/conf
export HIVE_CONF_DIR=/data/cdh/cloudera/parcels/CDH/lib/hive/conf
source /etc/profile
```

 to ./conf/spark-env.sh