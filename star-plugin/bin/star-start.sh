#!/bin/bash

# 关闭原有进程
for metaResult in $(ps -e -o pid,command | grep star-plugin.jar)
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

# 创建日志文件
STAR_LOG=../log/star-plugin.log
if [ ! -f "$STAR_LOG" ]; then
    touch "$STAR_LOG"
fi

# 启动项目
STAR_APP=../lib/star-plugin.jar
nohup java -jar -Xmx2048m "${STAR_APP}" --spring.config.location=../conf/application-star.yml >> "${STAR_LOG}" 2>&1 &
echo "部署成功"
