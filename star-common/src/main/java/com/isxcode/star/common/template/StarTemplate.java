package com.isxcode.star.common.template;

import com.isxcode.oxygen.core.http.HttpUtils;
import com.isxcode.star.common.constant.SecurityConstants;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.menu.ResponseEnum;
import com.isxcode.star.common.pojo.entity.StarRequest;
import com.isxcode.star.common.pojo.entity.StarResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StarTemplate {

    private final Map<String, String> header = new HashMap<>();

    private final String executeUrl;

    public StarTemplate(String host, String port, String key) {

        this.executeUrl = String.format(UrlConstants.BASE_URL, host, port);
        this.header.put(SecurityConstants.AUTH_ERROR_PATH, key);
    }

    public StarResponse executeSql(StarRequest starRequest) {

        try {
            return HttpUtils.doPost(executeUrl + UrlConstants.EXECUTE_SQL_URL, header, starRequest, StarResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new StarResponse(ResponseEnum.REMOTE_ERROR);
        }
    }

}
