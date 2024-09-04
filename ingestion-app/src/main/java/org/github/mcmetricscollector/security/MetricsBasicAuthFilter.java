package org.github.mcmetricscollector.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.github.mcmetricscollector.exceptions.UserNotEnabledException;
import org.github.mcmetricscollector.services.UserService;

import java.io.IOException;
import java.util.Base64;

@Slf4j
public class MetricsBasicAuthFilter implements Filter {

    private final UserService userService;

    public MetricsBasicAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String encodedToken = request.getHeader("Authorization");
        String basicToken = encodedToken.replace("Basic ", "");

        String decodedToken = new String(Base64.getDecoder().decode(basicToken));
        var userAndPassword = decodedToken.split(":");

        boolean credentialsOk;
        try {
            credentialsOk = userService.login(userAndPassword[0], userAndPassword[1]);
        } catch (UserNotEnabledException e) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (!credentialsOk) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
