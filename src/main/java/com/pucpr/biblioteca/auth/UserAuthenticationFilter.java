package com.pucpr.biblioteca.auth;

import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.service.JwtTokenService;
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
import java.util.Objects;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);
        if (token != null) {
            // Recupera o assunto do token
            String subject = jwtTokenService.getSubjectFromToken(token);
            // Cria um UserDetails com o usuário encontrado
            //MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(subject);
            //System.out.print("Subject (usuario) .: " + subject + "\n");
            //System.out.print("Usuário logado filter.: " + JwtTokenService.userDetails.getUsername() + "\n");
            if(subject != null) {
                // Cria um objeto de autenticação do Spring Security
                Authentication authentication = new UsernamePasswordAuthenticationToken(JwtTokenService.userDetails.getUsername(), null, JwtTokenService.userDetails.getAuthorities());
                // Define o objeto de autenticação no contexto de segurança do Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
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
