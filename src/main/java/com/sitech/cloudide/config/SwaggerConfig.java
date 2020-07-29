// package com.sitech.cloudide.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import io.swagger.annotations.Api;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @EnableSwagger2
// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .apiInfo(apiInfo())
//                 .select()
//                 .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                 .paths(PathSelectors.any())
//                 .build();
//     }

//     @Bean
//     public ApiInfo apiInfo() {
//         return new ApiInfoBuilder()
//                     .title("API标题")
//                     .description("API描述")
//                     .termsOfServiceUrl("http://localhost:8080")
//                     .version("1.0")
//                     .contact(new Contact("admin", "http://localhost:8080", "admin@admin.com"))
//                     .build();
//     }
// }