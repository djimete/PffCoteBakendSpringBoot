package sn.edu.isepdiamniadio.dbe.WorkingExpress.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI configureSwagger(){
        return new OpenAPI()
                .info(
                        new Info().title("Demo documentation et configuration")
                                .description("montre comment générer " +
                                        "une documentation OPEN API et " +
                                        "comment configurer une application " )
                                .version("1.0")
                                .contact(new Contact()
                                        .email("djimou.meta081@gmail.com")
                                        .name("Meta Djimou")
                                        .url("https://github.com/djimete")

                                )
                )

                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        new SecurityScheme()
                                                .name("bearerAuth")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("token")
                                )
                )
                ;


    }
}
