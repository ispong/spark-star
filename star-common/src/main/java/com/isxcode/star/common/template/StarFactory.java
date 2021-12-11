package com.isxcode.star.common.template;

import com.isxcode.star.common.properties.ServerInfo;
import com.isxcode.star.common.properties.StarProperties;

public class StarFactory {

    private static StarProperties starProperties;

    public StarFactory(StarProperties starProperties) {
        StarFactory.starProperties = starProperties;
    }

    public static StarTemplate build(String nodeName) {

        ServerInfo serverInfo = starProperties.getNodes().get(nodeName);
        return new StarTemplate(serverInfo.getHost(), serverInfo.getPort(), serverInfo.getPrivateKey());
    }

    public static StarTemplate build(String host, String port, String privateKey) {

        return new StarTemplate(host, port, privateKey);
    }

}
