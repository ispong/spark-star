##### Hive

- 3.1.2

##### 下载hive

```bash
nohup wget https://archive.apache.org/dist/hive/hive-3.1.2/apache-hive-3.1.2-bin.tar.gz >> download_hive.log 2>&1 &
#tail -f download_hive.log
```

##### 创建hive安装目录

```bash
sudo mkdir -p /data/hive
sudo chown -R ispong:ispong /data/hive
```

##### 解压hive

```bash
tar -vzxf apache-hive-3.1.2-bin.tar.gz -C /data/hive/
sudo ln -s /data/hive/apache-hive-3.1.2-bin /opt/hive
```

##### 配置系统环境变量

```bash
sudo vim /etc/profile

# === vim /etc/profile ===
export HIVE_HOME=/opt/hive 
export PATH=$PATH:$HIVE_HOME/bin 

# === vim /etc/profile ===
source /etc/profile
```

##### 版本检测

```bash
hive --version
```

##### 下载hive驱动

```bash
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.22.tar.gz
tar vzxf mysql-connector-java-8.0.22.tar.gz
cp mysql-connector-java-8.0.22/mysql-connector-java-8.0.22.jar /opt/hive/lib/
```

##### 创建hive的mysql数据库

```sql
-- docker exec -it isxcode-mysql bash
-- mysql -h localhost -u root -pisxcode123 -P 30102
CREATE USER IF NOT EXISTS 'ispong'@'%' identified by 'ispong123';
CREATE DATABASE IF NOT EXISTS hive_db DEFAULT CHARSET utf8mb4;
GRANT ALL PRIVILEGES on hive_db.* to 'ispong'@'%';
FLUSH PRIVILEGES;
```

##### 配置hive-log4j2.properties

```bash
mkdir -p /opt/hive/logs
cp /opt/hive/conf/hive-log4j2.properties.template /opt/hive/conf/hive-log4j2.properties

vim /opt/hive/conf/hive-log4j2.properties
# `/` property.hive.log.dir

# === vim /opt/hive/conf/hive-log4j2.properties ===
property.hive.log.dir=/opt/hive/logs
# === vim /opt/hive/conf/hive-log4j2.properties ===
```

##### 配置hive的环境变量

```bash
cp /opt/hive/conf/hive-env.sh.template /opt/hive/conf/hive-env.sh
vim /opt/hive/conf/hive-env.sh

# === vim /opt/hive/conf/hive-env.sh ===
export HADOOP_HOME=/opt/hadoop
export HIVE_CONF_DIR=/opt/hive/conf
# === vim /opt/hive/conf/hive-env.sh === 
```

##### 配置hive的mysql数据库

!> Note: # [3215,9] 3215行删掉描述  vim : 3215 dd

```bash
mkdir -p /data/hive/tmp

cp /opt/hive/conf/hive-default.xml.template /opt/hive/conf/hive-site.xml
vim /opt/hive/conf/hive-site.xml
```

```xml
<configuration>

    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://localhost:30102/hive_db</value>
    </property>
        
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.cj.jdbc.Driver</value>
    </property>
    
    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>ispong</value>
    </property>
    
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>ispong123</value>
    </property>

    <!-- beeline 账号 -->
    <property>
        <name>hive.server2.thrift.client.user</name>
        <!-- <value>${username}</value> -->
        <value>ispong</value>
    </property>
    
    <!-- beeline 密码 -->
    <property>
        <name>hive.server2.thrift.client.password</name>
        <!-- <value>${passwd}</value> -->
        <value>ispong123</value>
    </property>

    <!-- 直接新增 -->
    <property>
      <name>system:java.io.tmpdir</name>
      <value>/data/hive/tmp</value>
    </property>

    <!-- 直接新增,安装用户  -->
    <property>
      <name>system:user.name</name>
      <!-- <value>${installer}</value> -->
      <value>ispong</value>
    </property>
     
</configuration>
```

##### 升级guava版本

```bash
rm /opt/hive/lib/guava-19.0.jar
cp /opt/hadoop/share/hadoop/hdfs/lib/guava-27.0-jre.jar /opt/hive/lib/guava-27.0-jre.jar
```

##### 初始化数据库

```bash
schematool -dbType mysql -initSchema
# Initialization script completed
```

##### 启动hive

```bash
nohup hive --service metastore >> /opt/hive/logs/metastore.log 2>&1 &      
# tail -f /opt/hive/logs/metastore.log
# netstat -ntpl | grep 9083

# 日志报错: Caused by: java.lang.ClassNotFoundException: org.apache.tez.dag.api.TezConfiguration
# 要等待一会儿,大约5分钟
nohup hive --service hiveserver2 >> /opt/hive/logs/hiveserver2.log 2>&1 &   
# tail -f /opt/hive/logs/hiveserver2.log
# netstat -ntpl | grep 10000
```

##### 测试hive数据库

```bash
beeline -n ispong -p ispong123 -u jdbc:hive2://localhost:10000
```

```sql
CREATE TABLE IF NOT EXISTS default.demo_table(
    username   STRING  COMMENT 'comment for username',
    age        INT     COMMENT 'comment for age'
)
ROW FORMAT 
DELIMITED FIELDS TERMINATED BY '\001'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

INSERT INTO default.demo_table (username, age) VALUES ('ispong', 18);

SELECT * FROM default.demo_table;
```
