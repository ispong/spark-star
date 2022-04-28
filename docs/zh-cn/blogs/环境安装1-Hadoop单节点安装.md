##### Hadoop版本

- 3.2.2

##### 环境

- CentOS(7.9)
- 内网: 172.26.34.174
- 外网: 39.99.237.190

##### 创建用户ispong

```bash
useradd ispong
passwd ispong
vim /etc/sudoers
# / ## Allow root to run any commands anywhere
#ispong  ALL=(ALL) ALL
```

##### 下载hadoop

```bash
nohup wget https://archive.apache.org/dist/hadoop/common/hadoop-3.2.2/hadoop-3.2.2.tar.gz >> download_hadoop.log 2>&1 &
#tail -f download_hadoop.log
```

##### 创建安装目录

```bash
sudo mkdir -p /data/hadoop/
sudo chown -R ispong:ispong /data/hadoop/
```

##### 解压hadoop

```bash
tar -vzxf hadoop-3.2.2.tar.gz -C /data/hadoop/
sudo ln -s /data/hadoop/hadoop-3.2.2 /opt/hadoop
```

##### 安装java环境

```bash
sudo yum install -y java-1.8.0-openjdk-devel java-1.8.0-openjdk
```

##### 配置系统环境变量

```bash
sudo vim /etc/profile

# === sudo vim /etc/profile ===
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk

export HADOOP_HOME=/opt/hadoop
export HADOOP_CLASSPATH=/opt/hadoop/etc/hadoop:/opt/hadoop/share/hadoop/common/lib/*:/opt/hadoop/share/hadoop/common/*:/opt/hadoop/share/hadoop/hdfs:/opt/hadoop/share/hadoop/hdfs/lib/*:/opt/hadoop/share/hadoop/hdfs/*:/opt/hadoop/share/hadoop/mapreduce/lib/*:/opt/hadoop/share/hadoop/mapreduce/*:/opt/hadoop/share/hadoop/yarn:/opt/hadoop/share/hadoop/yarn/lib/*:/opt/hadoop/share/hadoop/yarn/* 
export PATH=$PATH:$HADOOP_HOME/bin 
export PATH=$PATH:$HADOOP_HOME/sbin 
# === sudo vim /etc/profile ===

source /etc/profile
```

##### 配置hadoop环境变量

```bash
vim /opt/hadoop/etc/hadoop/hadoop-env.sh
# `/` export JAVA_HOME=

# === vim /opt/hadoop/etc/hadoop/hadoop-env.sh ===
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
# === vim /opt/hadoop/etc/hadoop/hadoop-env.sh ===
```

##### 检查安装

```bash
hadoop version
```

##### 设置localhost免密

```bash
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys
```

##### 配置core-site.xml

```bash
vim /opt/hadoop/etc/hadoop/core-site.xml
```

```xml
<configuration>
    
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>

    <!-- 设置${user}ispong用户免权限 -->
    <property>
        <!-- <name>hadoop.proxyuser.${user}.hosts</name> -->
        <name>hadoop.proxyuser.ispong.hosts</name>
        <value>*</value>
    </property>

    <!-- 设置${user}ispong用户免权限 -->
    <property>
        <!-- <name>hadoop.proxyuser.${user}.groups</name> -->
        <name>hadoop.proxyuser.ispong.groups</name>
        <value>*</value>
    </property>
</configuration>
```

##### 配置hdfs-site.xml

```bash
vim /opt/hadoop/etc/hadoop/hdfs-site.xml
```

```xml
<configuration>

    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/data/hadoop/name</value>
    </property>

    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/data/hadoop/data</value>
    </property>

    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>

</configuration>
```

##### 格式化namenode

```bash
hdfs namenode -format
#  Storage directory /data/hadoop/name has been successfully formatted.
```

##### 配置服务器hostname

```bash
sudo hostnamectl set-hostname isxcode
```

##### 启动hdfs

```bash
start-dfs.sh
# stop-dfs.sh
```

- 39.99.237.190:9870

##### 配置mapred-site.xml

```bash
vim /opt/hadoop/etc/hadoop/mapred-site.xml
```

```xml
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
    <property>
        <name>mapreduce.application.classpath</name>
        <value>$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/*:$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/lib/*</value>
    </property>
</configuration>
```

##### 配置yarn-site.xml

```bash
vim /opt/hadoop/etc/hadoop/yarn-site.xml
```

```xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.nodemanager.env-whitelist</name>
        <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_HOME,PATH,LANG,TZ,HADOOP_MAPRED_HOME</value>
    </property>
</configuration>
```

##### 启动yarn

```bash
start-yarn.sh
#stop-yarn.sh
```

- 39.99.237.190:8088
