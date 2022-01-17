package com.isxcode.star.template.service;

import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TemplateService {

    private final StarTemplate starTemplate;

    public TemplateService(StarTemplate starTemplate) {
        this.starTemplate = starTemplate;
    }

    public void execute() {

        String sql = "insert into rd_dev.ispong_table(username, age, lucky_date) values ('ispong', 18, '2021-12-12 12:12:12')";

        StarRequest starRequest = StarRequest.builder()
            .executeId(UUID.randomUUID().toString())
            .sql(sql)
            .build();

        StarResponse starResponse = starTemplate.build().execute(starRequest);
        log.info("调用状态" + starResponse.toString());
    }

    public void executeQuery() {

        String sql = "select * from rd_dev.ispong_table";

        StarRequest starRequest = StarRequest.builder()
            .executeId(UUID.randomUUID().toString())
            .sql(sql)
            .limit(100)
            .build();

        StarResponse starResponse = starTemplate.build().executeQuery(starRequest);
        log.info("调用状态" + starResponse.toString());
    }

    public void executePageQuery() {

        String sql = "select * from rd_dev.ispong_table";

        StarRequest starRequest = StarRequest.builder()
            .executeId(UUID.randomUUID().toString())
            .sql(sql)
            .page(1)
            .pageSize(10)
            .limit(100)
            .build();

        StarResponse starResponse = starTemplate.build().executePageQuery(starRequest);
        log.info("调用状态" + starResponse.toString());
    }

    public void executeMultiSql() {

        List<String> sqlList = new ArrayList<>();
        sqlList.add("select * from rd_dev.ispong_table");
        sqlList.add("select * from rd_dev.ispong_table");

        StarRequest starRequest = StarRequest.builder()
            .executeId(UUID.randomUUID().toString())
            .sqls(sqlList)
            .build();

        StarResponse starResponse = starTemplate.build().executeMultiSql(starRequest);
        log.info("调用状态" + starResponse.toString());
    }
}
