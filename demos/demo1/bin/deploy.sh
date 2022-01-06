#!/bin/bash

# 获取环境变量
ENV=prod
for key in "$@"
do
    ENV=${key#*=}
done

# mvn打包
POM_PATH=../pom.xml
mvn clean package -f "${POM_PATH}"

# 关闭原有进程
for metaResult in $(ps -e -o pid,command | grep demo1-0.0.1.jar)
do
    if [ "$metaResult" == java ]; then
      STAR_PID=${PRE_VAL}
    	break
	fi
	PRE_VAL=${metaResult}
done
if [ _"${STAR_PID}" != _ ];then
  kill -9 "${STAR_PID}"
fi

# 如果日志文件不存在则创建
STAR_LOG_DIR=~/star-plugin/
if [ ! -d "$STAR_LOG_DIR" ]; then
    mkdir -p "$STAR_LOG_DIR"
fi
STAR_LOG=~/star-plugin/star-plugin.log
if [ ! -f "$STAR_LOG" ]; then
    touch "$STAR_LOG"
fi

# 重新解压把环境变量放到jar中
mkdir -p ../target/build
unzip ../target/demo1-0.0.1.jar -d ../target/build/
cp /data/cdh/cloudera/parcels/CDH/lib/hadoop/etc/hadoop/*.xml ../target/build/BOOT-INF/classes/
cd ../target/build/ || exit
jar -cvfM0 ../demo1-0.0.1.jar ./*

# 启动项目
STAR_APP=../demo1-0.0.1.jar
nohup java -jar -Xmx2048m "${STAR_APP}" --spring.profiles.active="${ENV}" >> "${STAR_LOG}" 2>&1 &
echo "部署成功"
