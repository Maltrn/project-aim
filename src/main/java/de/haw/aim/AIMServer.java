package de.haw.aim;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"de.haw.aim"})
public class AIMServer
{

    public static void main(String[] args)
    {
        run(AIMServer.class, args);
    }

    @Bean
    public Docket documentation()
    {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(metadata());
    }

    private ApiInfo metadata()
    {
        return new ApiInfoBuilder()
                .title("AIM REST-API")
                .description("Dokumentation der REST-API aus dem Projekt AIM")
                .version("2.0")
                .contact("info@aim.de")
                .build();
    }

    @Bean
    UiConfiguration uiConfig()
    {
        return new UiConfiguration("validatorUrl");
    }


}
