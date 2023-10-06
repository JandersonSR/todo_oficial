package todo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfiguration {
    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Rodando Configuracao");
        };
    }
}

// @Configuration
/*
    @Bean(name = "personName")
    public String personName() {
        return "Jon Doe";
    }
*/

// @Production
// @Profile("development") // roda apenas no ambiente informado



