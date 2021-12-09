### 前提

```bash
sudo vim /etc/profile

# === sudo vim /etc/profile ===
export HADOOP_HOME=/data/cdh/cloudera/parcels/CDH/lib/hadoop
export HADOOP_YARN_HOME=/data/cdh/cloudera/parcels/CDH/lib/hadoop-yarn
export SPARK_HOME=/data/cdh/cloudera/parcels/CDH/lib/spark
# === sudo vim /etc/profile ===

source /etc/profile
```

```http request
GET http://localhost:30156/demo
```