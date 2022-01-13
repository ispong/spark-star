package com.isxcode.star.common.template;

import com.isxcode.star.common.constant.SecurityConstants;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.properties.StarNodeProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * starTemplate 提供快捷方式访问
 */
@Service
@Slf4j
public class StarTemplate {

    private final StarNodeProperties starNodeProperties;

    public StarTemplate(StarNodeProperties starNodeProperties) {

        this.starNodeProperties = starNodeProperties;
    }

    public StarTemplate.Builder build() {

        return new Builder(starNodeProperties);
    }

    public StarTemplate.Builder build(String host, int port, String key) {

        return new Builder(host, port, key);
    }

    public static class Builder {

        private final StarNodeProperties starNodeProperties;

        public Builder(StarNodeProperties starNodeProperties) {

            this.starNodeProperties = starNodeProperties;
        }

        public Builder(String host, int port, String key) {

            StarNodeProperties starNodeProperties = new StarNodeProperties();
            starNodeProperties.setHost(host);
            starNodeProperties.setPort(port);
            starNodeProperties.setKey(key);
            this.starNodeProperties = starNodeProperties;
        }

        public StarResponse requestAcornServer(String url, StarRequest starRequest) {

            Map<String, String> headers = new HashMap<>();
            headers.put(SecurityConstants.HEADER_AUTHORIZATION, starNodeProperties.getKey());

            try {
                return HttpUtils.doPost(url, headers, starRequest, StarResponse.class);
            } catch (IOException e) {
                return new StarResponse("500", e.getMessage());
            }
        }

        public StarResponse executeSql(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.EXECUTE_SQL_URL, starNodeProperties.getHost(), starNodeProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

    }

}
