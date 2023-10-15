package com.aeroapp.aeroapp.Config.Swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact[] contacts = new Contact[] {
                new Contact("Andrés Torrés",
                        "https://www.linkedin.com/in/andrés-sebastian-torres-mesa-565166265/",
                        "andressebastiantorresmesa@gmail.com"),
                new Contact("Juan Pablo González",
                        "www.linkedin.com/in/jpabgonzalez",
                        "jupago14@gmail.com")
        };

        return new ApiInfoBuilder().title("API para gestión de Sistema de Vuelos")
                .description("Este sistema de manejo de vuelos está diseñado como una aplicación Spring Boot que " +
                        "utiliza Spring Security JWT para la autenticación y autorización, incluye Swagger para " +
                        "documentar la API y sigue una arquitectura monolítica.")
                .version("1.0.0")
                .contact(contacts[0])
                .build();
    }

}