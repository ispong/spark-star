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
chmod a+x "${ACORN_BUILD_DIR}"/bin/*.sh
echo "创建 bin 成功"

# 复制conf文件夹
mkdir -p "${STAR_BUILD_DIR}"/conf
cp "${BASE_PATH}"/star-plugin/conf/* "$STAR_BUILD_DIR"/conf
echo "创建 conf 成功"

# 创建log文件夹
mkdir -p "${STAR_BUILD_DIR}"/log
echo "创建 log 成功"

# 创建lib文件夹
mkdir -p "${STAR_BUILD_DIR}"/lib
cp "${BASE_PATH}"/star-plugin/target/star-plugin.jar "${STAR_BUILD_DIR}"/lib/star-plugin.jar
echo "创建 lib 成功"

# 创建plugins文件夹
mkdir -p "${STAR_BUILD_DIR}"/plugins
cp "${BASE_PATH}"/star-executor/target/star-executor.jar "${STAR_BUILD_DIR}"/plugins/star-executor.jar
echo "创建 plugins 成功"
