#### 本地直接查询sql

```http request
http://192.168.16.75:30156/spark-star/quick/executeQuery
Content-Type: application/json
Star-Key: star-key
```

```json
{
    "sql": "select * from cdh_dev.part limit 10",
    "limit": 10 
}
```

