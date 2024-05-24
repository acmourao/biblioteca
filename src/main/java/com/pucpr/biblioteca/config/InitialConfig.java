package com.pucpr.biblioteca.config;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.AcervoRepository;
import com.pucpr.biblioteca.repository.CategoriaRepository;
import com.pucpr.biblioteca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitialConfig {
    @Bean
    public CommandLineRunner loadInitialData(CategoriaRepository categoriaRepository, UserRepository userRepository, AcervoRepository acervoRepository) {
        return (args) -> {
            categoriaRepository.save(new Categoria("LIVRO"));
            categoriaRepository.save(new Categoria("REVISTA"));
            categoriaRepository.save(new Categoria("PERIODICO"));
            categoriaRepository.save(new Categoria("PINTURA"));
            categoriaRepository.save(new Categoria("FOTOGRAFIA"));
            categoriaRepository.save(new Categoria("ESTÁTUA"));
            categoriaRepository.save(new Categoria("ABSTRATO"));
            categoriaRepository.save(new Categoria("OUTROS"));

            User user = new User();
            user.setUsername("anderson");
            user.setEmail("aaaaaaaa@gmail.com");
            user.setPassword("$2a$10$8.dlQ/tekBWcBH5Wls.fVO2yV3O264Na1ZxROaLDtgtJAiM8.liP6");
            user.setRole("ROLE_ADMIN");
            user.setTelefone("61999999999");
            userRepository.save(user);

            Acervo acervo1 = new Acervo();
            acervo1.setTitulo("Arquiteturas de TI");
            acervo1.setAutor("Equipe redação do Caderno de Inovação");
            acervo1.setCategoria(categoriaRepository.findById(1).orElse(null));
            acervo1.setPublicacao(2013);
            acervoRepository.save(acervo1);

            Acervo acervo2 = new Acervo();
            acervo2.setTitulo("Dinheiro: os segredos de quem tem");
            acervo2.setAutor("Cerbasi, Gustavo Petrasunas");
            acervo2.setCategoria(categoriaRepository.findById(1).orElse(null));
            acervo2.setPublicacao(2010);
            acervo2.setActive(false);
            acervoRepository.save(acervo2);

            Acervo acervo3 = new Acervo();
            acervo3.setTitulo("Brasília");
            acervo3.setAutor("Companhia Urbanizadora da Nova Capital do Brasil");
            acervo3.setCategoria(categoriaRepository.findById(3).orElse(null));
            acervo3.setPublicacao(1957);
            acervoRepository.save(acervo3);

            Acervo acervo4 = new Acervo();
            acervo4.setTitulo("Indexação de revistas");
            acervo4.setAutor("Universidade do Minho");
            acervo4.setCategoria(categoriaRepository.findById(2).orElse(null));
            acervo4.setPublicacao(2022);
            acervoRepository.save(acervo4);

        };
    }
}
