package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(findByUsername(username));
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        try {
            if (user == null) {
                throw new UsernameNotFoundException("Usuário não encontrado pelo nome!");
            }
        } catch (UsernameNotFoundException e) {
            logger.error(e.getMessage());
        }
        return user;
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
                .orElseThrow(() -> new ServiceException("Usuário não encontrado pelo Id!"));
    }

    public User editar(UserDTO userDTO) {
        User user = getUserLogado();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRole(userDTO.role());
        user.setTelefone(userDTO.telefone());
        userRepository.save(user);
        logger.info("Id {} .: {} foi editado!", user.getId().toString(), user.getUsername());
        return user;
    }

    public String deletar() {
        User user = getUserLogado();
        userRepository.delete(user);
        logger.info("{} foi apagado!", user.getUsername());
        return "redirect:/";
    }


    public User getUserLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findByUsername(username);
        logger.info("Usuário Logado .: {}", user.getUsername());
        return user;
    }

}
