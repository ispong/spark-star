package com.isxcode.star.common.service;

import com.alibaba.fastjson.JSON;
import com.isxcode.oxygen.core.http.HttpUtils;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.menu.ResponseEnum;
import com.isxcode.star.common.pojo.entity.ExecuteConfig;
import com.isxcode.star.common.pojo.entity.StarResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StarTemplate {

    public StarResponse executeSql(String host, String port, String key, ExecuteConfig executeConfig) {

        try {
            String executeUrl = String.format(UrlConstants.EXECUTE_SQL_URL, host, port);
            return HttpUtils.doPost(executeUrl, executeConfig, StarResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new StarResponse(ResponseEnum.REMOTE_ERROR);
        }
    }

}
