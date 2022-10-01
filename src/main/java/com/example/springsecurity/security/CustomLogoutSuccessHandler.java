package com.example.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    public static final Logger LOG = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();

            LOG.info("onLogout Success - user logged out:" + user.getUsername());

            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("user");
            }

            redirectStrategy.sendRedirect(request, response, "/login");
        }
    }
}
