package com.isxcode.star.template.service;

import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarTemplate;
import com.isxcode.star.template.pojo.TemplateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

    private final StarTemplate starTemplate;

    public void executeQuery() {

        String sql = "select * from default.demo_table";

        StarRequest starRequest = StarRequest.builder()
            .sql(sql)
            .build();

        StarResponse starResponse = starTemplate.build("worker1").quickExecuteQuery(starRequest);
        log.info("调用状态" + starResponse.toString());
    }

    public void execute(TemplateReq templateReq) {

        StarRequest starRequest = StarRequest.builder()
            .executeId(UUID.randomUUID().toString())
            .sql(templateReq.getSql())
            .build();

        StarResponse starResponse = starTemplate.build().execute(starRequest);
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
