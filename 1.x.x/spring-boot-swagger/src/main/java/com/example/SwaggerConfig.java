package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by nasruddin on 16/10/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket movieApi() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("movie-api")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                    .paths(regex("/api.*"))
                .build();

    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple Movie API")
                .description("Api for Movies")
                .version("1.0")
                .build();
    }
}
