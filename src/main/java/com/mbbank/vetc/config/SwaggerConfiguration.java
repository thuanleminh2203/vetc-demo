package com.mbbank.vetc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Autowired
    ServletContext servletContext;

    @Value("${swagger.basePath:/}")
    private String basePath;

    @Value("${mb.oauth2.url:}")
    private String mbOAuthUrl;

    private final String schemaName = "oAuth";

    @Bean
    public Docket api() {
        List<Response> globalResponseList = List.of(
                new ResponseBuilder().code("500")
                        .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .build(),
                new ResponseBuilder().code("400")
                        .description(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mbbank.vetc.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .globalResponses(HttpMethod.GET, globalResponseList)
                .globalResponses(HttpMethod.POST, globalResponseList)
                ;
    }


    private ApiInfo getApiInfo() {
        Contact contact = new Contact("ati.vn", "https://ati.vn", "contact@ati.vn");
        return new ApiInfoBuilder()
                .title("VETC")
                .description("This is Home page")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(createApiKeyScheme());
    }

    private ApiKey createApiKeyScheme() {
        return new ApiKey(schemaName, "Authorization", "header");
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(
                                        new SecurityReference(schemaName, authorizationScopes)))
                        .build());
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }


    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .build();
    }

}
