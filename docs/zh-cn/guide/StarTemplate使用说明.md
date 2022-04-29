#### 查询hive中表的数据

```java
class Demo{
    public void executeQuery() {

        String sql = "select * from default.demo_table";

        StarRequest starRequest = StarRequest.builder()
            .sql(sql)
            .build();

        StarResponse starResponse = starTemplate.build("worker1").quickExecuteQuery(starRequest);
        log.info("调用状态" + starResponse.toString());
    }
}
```

```json
{
    "code": "200",
    "message": "查询成功",
    "starData": {
        "columnNames": [
            "username",
            "age"
        ],
        "dataList": [
            ["ispong", "18"],
            ["ispong", "19"]
        ]
    }
}
```

##### 执行sql


##### 分页查询数据


##### 多行sql执行


##### 查看运行日志


##### 查看sql运行状态


##### 停止sql运行
