package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pucpr.biblioteca.dto.LoginUserDTO;

@Service
public class JwtUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService tokenService;

    public String authenticateUser(LoginUserDTO loginUserDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDTO.username(), loginUserDTO.password());
        Authentication authentication = authenticationManager.authenticate(token);
//        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return tokenService.generateToken(authentication.getName());
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setRole(userDTO.role());
        user.setEmail(userDTO.email());
        user.setTelefone(userDTO.telefone());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return userRepository.save(user);
    }
}
