package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        try {
            if (user == null) {
                throw new UsernameNotFoundException("Usuário não encontrado!");
            }
        } catch (UsernameNotFoundException e) {
            logger.error(e.getMessage());
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

    public User editar(UserDTO userDTO) {
        User user = findById(userDTO.id());
        user.setEmail(userDTO.email());
        user.setTelefone(userDTO.telefone());
        userRepository.save(user);
        logger.info(user.getUsername() + " foi editado!" );
        return user;
    }

    public void deletar() {
        User user = getUserLogado();
        userRepository.delete(user);
        logger.info(user.getUsername() + " foi apagado!" );
    }


    public User getUserLogado() {
        return authenticationFacade.getUser();
    }
}
