package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Usuário não cadastrado!");
        }
        return new MyUserDetails(user);
    }

    
}
