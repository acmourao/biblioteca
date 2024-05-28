package com.pucpr.biblioteca.auth;

import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.service.JwtTokenService;
import com.pucpr.biblioteca.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);
        if (token != null) {
            // Recupera o assunto do token
            String subject = jwtTokenService.getSubjectFromToken(token);
            if (subject != null) {
                // Busca o usuário pelo assunto (username, email, nick ..)
                try {
                    MyUserDetails userDetails = userService.loadUserByUsername(subject);
                    // Cria um objeto de autenticação do Spring Security
                    Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, userDetails.getAuthorities());
                    // Define o objeto de autenticação no contexto de segurança do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception ignore) {
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
