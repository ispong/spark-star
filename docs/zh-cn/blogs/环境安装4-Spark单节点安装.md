##### 下载spark

```bash
nohup wget https://archive.apache.org/dist/spark/spark-3.1.1/spark-3.1.1-bin-hadoop3.2.tgz >> download_spark.log 2>&1 &  
#tail -f download_spark.log
```

##### 创建安装目录

```bash
sudo mkdir -p /data/spark
sudo chown -R ispong:ispong /data/spark
```

##### 解压spark

```bash
tar -vzxf spark-3.1.1-bin-hadoop3.2.tgz -C /data/spark
sudo ln -s /data/spark/spark-3.1.1-bin-hadoop3.2 /opt/spark
```

##### 配置环境变量

```bash
sudo vim /etc/profile

# === vim /etc/profile ===
export SPARK_HOME=/opt/spark 
export PATH=$PATH:$SPARK_HOME/bin  
# === vim /etc/profile ===

source /etc/profile
```

##### 配置spark环境变量

```bash
cp /opt/spark/conf/spark-env.sh.template /opt/spark/conf/spark-env.sh
vim /opt/spark/conf/spark-env.sh

# === vim /opt/spark/conf/spark-env.sh ===
export HADOOP_CONF_DIR=/opt/hadoop/ect/hadoop
export YARN_CONF_DIR=/opt/hadoop/ect/hadoop
# === vim /opt/spark/conf/spark-env.sh ===
```

##### 配置capacity-scheduler

```bash
vim /opt/hadoop/etc/hadoop/capacity-scheduler.xml
```

```xml
<property>
  <name>yarn.scheduler.capacity.root.queues</name>
  <value>default,isxcode</value>
</property>
```

##### 将hive中的hive-site.xml拷贝到spark的conf下面

```bash
cp /opt/hive/conf/hive-site.xml /opt/spark/conf
```

##### 将mysql驱动复制到spark的jars下面

```bash
cp mysql-connector-java-8.0.22/mysql-connector-java-8.0.22.jar /opt/spark/jars/
```

##### spark测试

```bash
# 查看版本
spark-submit --version

# 通过spark-shell查询hive中的数据
spark-shell
```

```scala
spark.sql("SELECT * FROM default.demo_table").show
```
