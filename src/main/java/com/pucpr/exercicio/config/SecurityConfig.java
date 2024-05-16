package com.pucpr.exercicio.config;

import com.pucpr.exercicio.auth.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/user/**",
            "/user/",
            "/api/auth/**",
            "/api/test/**",
    };

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterApiChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/exercicio/helloAdmin/**").hasRole("ADMIN_1")
                .anyRequest().authenticated()
        );
        http.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
