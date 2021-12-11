package com.isxcode.star.plugin.filter;

import com.isxcode.star.common.constant.SecurityConstants;
import com.isxcode.star.common.properties.StarProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final StarProperties starProperties;

    public JwtAuthenticationFilter(StarProperties starProperties) {

        this.starProperties = starProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 验证请求头
        String authorization = request.getHeader(SecurityConstants.HEADER_AUTHORIZATION);
        if (authorization == null) {
            request.getRequestDispatcher(SecurityConstants.TOKEN_IS_NULL_PATH).forward(request, response);
            return;
        }

        if (!authorization.equals(starProperties.getPrivateKey())) {
            request.getRequestDispatcher(SecurityConstants.AUTH_ERROR_PATH).forward(request, response);
            return;
        }

        doFilter(request, response, filterChain);
    }


}
