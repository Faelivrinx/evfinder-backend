package com.mypieceofcode.evfinder.config;

import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    private UserService userService;

    @Autowired
    public TokenFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Token!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isAuthorization(request)) {
            User user = userService.findUserByApiKey(getAuthorization(request));
            if (user != null) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                setUnauthorized(response);
            }
        } else {
            setUnauthorized(response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isAuthorization(HttpServletRequest request) {
        return getAuthorization(request) != null;
    }

    private void setUnauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {

    }
}
