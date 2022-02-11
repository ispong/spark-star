## 版本3.1.1

##### 发布

```bash
spark-submit --class com.isxcode.star.demo2.Demo \
    --master yarn \
    --deploy-mode cluster \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 4 \
    --queue default \
    target/demo2-0.0.1.jar
```
