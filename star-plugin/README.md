##### 使用前提

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

###### 或者

```bash
# 或者 git clone https://gitee.com/ispong/spark-star.git
git clone https://github.com/ispong/spark-star.git
# 构建插件
cd star-plugin && mvn clean package
# 添加hadoop配置文件
cd target && mkdir build && unzip star-plugin.jar -d ./build
cp ${HADOOP_HOME}/etc/hadoop/* ./build/BOOT-INF/classes/
cd ./build/ && jar -cvfM0 star-plugin.jar ./
```