package todo.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentation - Rest TODO API")
                        .description("Documentation - Rest TODO API")
                        .version("0.1")
                        .termsOfService("Open Source")
                        .license(new License()
                                .name("Teste 2.0")
                                .url("https://github.com/AGX-Software")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("agxSoftware")
                        .url("https://github.com/AGX-Software")
                );
    }
}
