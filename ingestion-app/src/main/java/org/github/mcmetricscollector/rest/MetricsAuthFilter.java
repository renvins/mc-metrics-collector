package org.github.mcmetricscollector.rest;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@Slf4j
public class MetricsAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().equals("/v1/metrics")) {
            String encodedToken = request.getHeader("Authorization");
            String basicToken = encodedToken.replace("Basic ", "");

            String decodedToken = new String(Base64.getDecoder().decode(basicToken));
            if (!decodedToken.equals("admin:admin")) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
