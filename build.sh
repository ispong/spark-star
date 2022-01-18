## 获取当前路径
current_path=`pwd`
case "`uname`" in
    Linux)
        bin_abs_path=$(readlink -f $(dirname $0))
        ;;
    *)
        bin_abs_path=`cd $(dirname $0); pwd`
        ;;
esac
BASE_PATH=${bin_abs_path}
echo "获取当前路径:"+ "${BASE_PATH}"

# 检查hadoop_home 环境变量
if [ _"${HADOOP_HOME}" == _ ];then
   echo "请检查环境变量 HADOOP_HOME"
fi

# 打包
echo "开始打包"
mvn clean package -Dmaven.test.skip -pl star-common,star-plugin,star-executor || exit
echo "打包成功"

# 创建star文件
STAR_BUILD_DIR="${BASE_PATH}"/star
if [ -d "${STAR_BUILD_DIR}" ]; then
    rm -rf "${STAR_BUILD_DIR}"
fi
mkdir -p "${STAR_BUILD_DIR}"
echo "创建 STAR_BUILD_DIR 成功"

# 复制bin文件夹
mkdir -p "${STAR_BUILD_DIR}"/bin
cp "${BASE_PATH}"/star-plugin/bin/* "${STAR_BUILD_DIR}"/bin
chmod a+x "${STAR_BUILD_DIR}"/bin/*.sh
echo "创建 bin 成功"

# 把系统的yarn配置文件放进去
TMP_BUILD_DIR=${BASE_PATH}/tmp_build
if [ -d "${TMP_BUILD_DIR}" ]; then
    rm -rf "${TMP_BUILD_DIR}"
fi
mkdir -p "${TMP_BUILD_DIR}"
unzip "${BASE_PATH}"/star-plugin/target/star-plugin.jar -d "${TMP_BUILD_DIR}"/
cp "${HADOOP_HOME}"/etc/hadoop/* "${TMP_BUILD_DIR}"/BOOT-INF/classes/
cd "${TMP_BUILD_DIR}" && jar -cvfM0 star-plugin.jar ./*

# 创建lib文件夹
mkdir -p "${STAR_BUILD_DIR}"/lib
cp "${TMP_BUILD_DIR}"/star-plugin.jar "${STAR_BUILD_DIR}"/lib/star-plugin.jar
cp "${BASE_PATH}"/star-common/target/star-common.jar "${STAR_BUILD_DIR}"/lib/star-common.jar
wget -p "${STAR_BUILD_DIR}"/lib/ https://repo1.maven.org/maven2/com/alibaba/fastjson/1.2.79/fastjson-1.2.79.jar
echo "创建 lib 成功"

# 复制conf文件夹
mkdir -p "${STAR_BUILD_DIR}"/conf
cp "${BASE_PATH}"/star-plugin/conf/* "$STAR_BUILD_DIR"/conf
echo "创建 conf 成功"

# 创建log文件夹
mkdir -p "${STAR_BUILD_DIR}"/log
echo "创建 log 成功"

# 创建plugins文件夹
mkdir -p "${STAR_BUILD_DIR}"/plugins
cp "${BASE_PATH}"/star-executor/target/star-executor.jar "${STAR_BUILD_DIR}"/plugins/star-executor.jar
echo "创建 plugins 成功"
