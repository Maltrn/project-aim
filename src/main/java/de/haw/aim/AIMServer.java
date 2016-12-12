package de.haw.aim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"de.haw.aim"})
public class AIMServer
{

    public static void main(String[] args)
    {
        SpringApplication.run(AIMServer.class, args);
    }

    @Bean
    public Docket documentation()
    {
        Docket ret = new Docket(
                DocumentationType.SWAGGER_2).
                select().
                apis(RequestHandlerSelectors.basePackage("de.haw.aim")).
                paths(PathSelectors.any()).
                build().
                pathMapping("/").
                apiInfo(metadata());
        ret.useDefaultResponseMessages(false);
        return ret;
    }

    private ApiInfo metadata()
    {
        return new ApiInfoBuilder()
                .title("AIM REST-API")
                .description("Dokumentation der REST-API aus dem Projekt AIM")
                .version("2.0")
                .contact(new Contact("AIM", "", "info@aim.de"))
                .build();
    }

    @Bean
    UiConfiguration uiConfig()
    {
        return new UiConfiguration("validatorUrl");
    }

}
