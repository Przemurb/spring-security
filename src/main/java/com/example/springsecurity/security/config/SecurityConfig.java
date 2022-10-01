package com.example.springsecurity.security.config;


import com.example.springsecurity.security.CustomAuthFailureHandler;
import com.example.springsecurity.security.CustomAuthSuccessHandler;
import com.example.springsecurity.security.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthFailureHandler customAuthFailureHandler;
    private final CustomAuthSuccessHandler customAuthSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    public SecurityConfig(CustomAuthFailureHandler customAuthFailureHandler,
                          CustomAuthSuccessHandler customAuthSuccessHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.customAuthFailureHandler = customAuthFailureHandler;
        this.customAuthSuccessHandler = customAuthSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/login*", "/home", "/h2-console/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .successHandler(customAuthSuccessHandler)
                .failureUrl("/login?error=true")
                .failureHandler(customAuthFailureHandler);
        http.logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .invalidateHttpSession(false)
                .deleteCookies("JSESSIONID")
                .and().logout();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers("/styles/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

