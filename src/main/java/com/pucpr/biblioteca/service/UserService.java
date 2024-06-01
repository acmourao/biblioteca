package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.Locacao;
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
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocacaoService locacaoService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(findByUsername(username));
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        try {
            if (user == null)
                throw new UsernameNotFoundException("Usuário não encontrado pelo nome!");
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

    public MyUserDetails bloquearUserById(Long id) {
        User user = findById(id);
        if (locacaoService.countByUser(user) > 0)
            throw new RuntimeException("Usuário tem locação pendente, não pode ser bloqueado!");
        user.setActive(false);
        return new MyUserDetails(userRepository.save(user));
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException("Usuário não encontrado pelo Id!"));
    }

    public User manterUser(User tmp) {
        User user = findById(tmp.getId());
        user.setEmail(tmp.getEmail());
        user.setTelefone(tmp.getTelefone());
        user.setRole(tmp.getRole());
        user.setActive(tmp.isActive());
        loggerinfo(user);
        return userRepository.save(user);
    }

    public User manterUser(UserDTO userDTO) {
        User user = getUserLogado();
        user.setEmail(userDTO.email());
        user.setTelefone(userDTO.telefone());
        loggerinfo(user);
        return userRepository.save(user);
    }

    private void loggerinfo(User user) {
        logger.info("Id {} .: {} foi consultado!", user.getId().toString(), user.getUsername());
    }

    public String deletar() {
        return delete(getUserLogado());
    }

    public String deletar(Long id) {
        return delete(findById(id));
    }

    private String delete(User user) {
        loggerinfo(user);
        userRepository.delete(user);
        return "redirect:/";
    }

    public Locacao devolverAcervoUserLogado(Long idAcervo) {
        Locacao locacao = locacaoService.findByAcervoId(idAcervo);
        if (!locacao.getUser().getUsername().equals(authenticationFacade.getUser().getUsername()))
            throw new RuntimeException("Usuário logado não locou este acervo!");
        return locacaoService.baixarLocacao(locacao);
    }

    public User getUserLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }

}
