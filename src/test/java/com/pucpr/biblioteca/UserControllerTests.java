package com.pucpr.biblioteca;

import com.pucpr.biblioteca.controller.UserController;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @MockBean
    private UserRepository userRepository;

    @Test
    void addUserShouldReturnUser() {
        User user = new User();
        user.setId(9L);
        user.setUsername("John Doe");
        user.setEmail("john@pucpr.br");
        user.setPassword("1234567890");
        when(userRepository.save(any())).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        User returnedUser = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(returnedUser).isEqualTo(user);
    }

}
