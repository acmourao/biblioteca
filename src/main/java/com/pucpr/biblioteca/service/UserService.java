package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //    @Override
//    public MyUserDetails loadUserByUsername(String username) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
//        return new MyUserDetails(user);
//    }
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
                .orElse(null);
    }

    public User getUserLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.print("\nUsuário Logado .: " + username + "\n");
        return userRepository.findByUsername(username);
    }
}
