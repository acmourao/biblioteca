package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.entity.MyUserDetails;
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

    @Override
    public MyUserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(null);
        }
        return new MyUserDetails(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado para este Id!");
        }
        return user;
    }

    public MyUserDetails findUserDetailById(long id) {
        return new MyUserDetails( findUserById(id) );
    }

//    public User loadUserLogado() {
//        return ( loadUserByUsername(authenticationFacade.getUsername() ).getUser() );
//    }
}
