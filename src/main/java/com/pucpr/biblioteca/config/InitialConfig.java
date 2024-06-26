package com.pucpr.biblioteca.config;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.AcervoRepository;
import com.pucpr.biblioteca.repository.CategoriaRepository;
import com.pucpr.biblioteca.repository.UserRepository;
import com.pucpr.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitialConfig {

    @Autowired
    private CategoriaService categoriaService;

    @Bean
    public CommandLineRunner loadInitialData(CategoriaRepository categoriaRepository, UserRepository userRepository, AcervoRepository acervoRepository) {
        return (args) -> {
            categoriaRepository.save(new Categoria("LIVRO"));
            categoriaRepository.save(new Categoria("REVISTA"));
            categoriaRepository.save(new Categoria("PERIODICO"));
            categoriaRepository.save(new Categoria("PINTURA"));
            categoriaRepository.save(new Categoria("FOTOGRAFIA"));

            userRepository.save(new User(
                    "administrador",
                    "$2a$10$xtfayZf0FXUuz2HdCAe/puvhcpEtierSF5wp4knBulreepSbRopP6",
                    //"$2a$10$XhLvT0MbGECZ7E4cLemYdePBnZu8HPNlxWrjjKharAc9MUVMy.zL6",
                    "ROLE_ADMIN",
                    true,
                    "administrador@live.com",
                    "8599999999"));

            Acervo acervo1 = new Acervo(
                    "Arquiteturas de TI",
                    "Equipe redação do Caderno de Inovação",
                    2013,
                    categoriaService.findById(1));
            acervoRepository.save(acervo1);

            Acervo acervo2 = new Acervo(
                    "Dinheiro: os segredos de quem tem",
                    "Cerbasi, Gustavo Petrasunas",
                    2010,
                    categoriaService.findById(1));
            acervoRepository.save(acervo2);

            Acervo acervo3 = new Acervo(
                    "Indexação de revistas",
                    "Universidade do Minho",
                    2022,
                    categoriaService.findById(2));
            acervoRepository.save(acervo3);

            Acervo acervo4 = new Acervo(
                    "Código Limpo: Habilidades Práticas do Agile Software",
                    "Robert C. Martin - Uncle Bob",
                    2009,
                    categoriaService.findById(1));
            acervoRepository.save(acervo4);

            Acervo acervo5 = new Acervo(
                    "Brasília",
                    "Companhia Urbanizadora da Nova Capital do Brasil",
                    1957,
                    categoriaService.findById(3));
            acervoRepository.save(acervo5);

            Acervo acervo6 = new Acervo(
                    "Laranja Mecânica",
                    "Anthony Burgess",
                    1962,
                    categoriaService.findById(1));
            acervoRepository.save(acervo6);


        };
    }
}
