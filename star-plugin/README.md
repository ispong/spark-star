##### 使用前提

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