package com.pucpr.biblioteca.auth;

import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(AuthenticationFacade.class);

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("Usuário Logado .: " + username);
        //System.out.print("\nUsuário Logado .: " + username + "\n");
        return userRepository.findByUsername(username);
    }
}
