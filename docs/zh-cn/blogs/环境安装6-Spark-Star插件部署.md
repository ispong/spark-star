##### 下载spark-star源码

```bash
# sudo yum install -y git
git clone https://github.com/ispong/spark-star.git
```

##### 修改pom文件中的组件版本

```bash
vim /home/ispong/spark-star/star-plugin/pom.xml
```

```xml
<properties>
    <spark.version>3.1.1</spark.version>
    <scala.version>2.12</scala.version>
    <hive.version>3.1.2</hive.version>
    <hadoop.version>3.2.2</hadoop.version>
</properties>
```

##### 编译项目

```bash
# sudo yum install -y maven
# sudo yum install -y unzip 
cd spark-star
nohup bash build.sh >> build.log 2>&1 &
tail -f build.log

# 创建软链接
sudo ln -s ~/spark-star/build /opt/spark-star
```

##### 配置系统环境变量

```bash
sudo vim /etc/profile

# === sudo vim /etc/profile ===
export SPARK_STAR_HOME=/opt/spark-star
export PATH=$PATH:$SPARK_STAR_HOME/bin
# === sudo vim /etc/profile ===

source /etc/profile
```

##### 启动插件

```bash
# 查看版本
spark-star version
# 启动插件
spark-star start
# 查看日志
spark-star log
# 查看状态
spark-star status
# 编辑配置文件
spark-star config
# 关闭插件
spark-star stop
```
