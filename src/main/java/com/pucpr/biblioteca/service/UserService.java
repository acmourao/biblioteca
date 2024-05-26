package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return new MyUserDetails(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public MyUserDetails findUserDetailById(Long id) {
        return new MyUserDetails(findById(id));
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow();
    }

    public User getUserLogado() {
        return authenticationFacade.getUser();
    }
}
