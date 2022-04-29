package com.isxcode.star.common.template;

import com.isxcode.star.common.constant.SecurityConstants;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.properties.StarClientProperties;
import com.isxcode.star.common.properties.WorkerProperties;
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

    private final StarClientProperties starClientProperties;

    public StarTemplate(StarClientProperties starClientProperties) {

        this.starClientProperties = starClientProperties;
    }

    public StarTemplate.Builder build(String workerName) {

        WorkerProperties workerProperties = starClientProperties.getWorkers().get(workerName);
        return new Builder(workerProperties);
    }

    public StarTemplate.Builder build() {

        return build("default");
    }

    public StarTemplate.Builder build(String host, int port, String key) {

        return new Builder(host, port, key);
    }

    public static class Builder {

        private final WorkerProperties workerProperties;

        public Builder(WorkerProperties workerProperties) {

            this.workerProperties = workerProperties;
        }

        public Builder(String host, int port, String key) {

            WorkerProperties workerProperties = new WorkerProperties();
            workerProperties.setHost(host);
            workerProperties.setPort(port);
            workerProperties.setKey(key);
            this.workerProperties = workerProperties;
        }

        public StarResponse requestAcornServer(String url, StarRequest starRequest) {

            Map<String, String> headers = new HashMap<>();
            headers.put(SecurityConstants.HEADER_AUTHORIZATION, workerProperties.getKey());

            try {
                return HttpUtils.doPost(url, headers, starRequest, StarResponse.class);
            } catch (IOException e) {
                return new StarResponse("500", e.getMessage());
            }
        }

        public StarResponse execute(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.EXECUTE_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse executeQuery(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.EXECUTE_QUERY_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse executePageQuery(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.EXECUTE_PAGE_QUERY_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse executeMultiSql(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.EXECUTE_MULTI_SQL_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse getLog(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.GET_JOB_LOG_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse stopJob(StarRequest starRequest) {

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.STOP_JOB_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }

        public StarResponse quickExecuteQuery(StarRequest starRequest) {

            if (starRequest.getLimit() == null) {
                starRequest.setLimit(100);
            }

            String executeUrl = String.format(UrlConstants.BASE_URL + UrlConstants.QUICK_EXECUTE_QUERY_URL, workerProperties.getHost(), workerProperties.getPort());
            return requestAcornServer(executeUrl, starRequest);
        }
    }

}
