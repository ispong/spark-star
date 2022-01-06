package com.isxcode.star.plugin.filter;

import com.isxcode.star.common.constant.SecurityConstants;
import com.isxcode.star.common.properties.StarPluginProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class StarKeyFilter extends OncePerRequestFilter {

    private final StarPluginProperties starPluginProperties;

    public StarKeyFilter(StarPluginProperties starPluginProperties) {

        this.starPluginProperties = starPluginProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 检查key是否存在
        String authorization = request.getHeader(SecurityConstants.HEADER_AUTHORIZATION);
        if (authorization == null) {
            request.getRequestDispatcher(SecurityConstants.KEY_IS_NULL_EXCEPTION).forward(request, response);
            return;
        }

        // 检查key是否合法
        if (!authorization.equals(starPluginProperties.getServerKey())) {
            request.getRequestDispatcher(SecurityConstants.KEY_IS_ERROR_EXCEPTION).forward(request, response);
            return;
        }

        doFilter(request, response, filterChain);
    }

}
