package com.github.mrduguo.spring.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${api.name:}")
    String projectName;

    @Value("${api.description:}")
    String projectDescription;

    @Value("${api.version:}")
    String projectVersion;

    @Value("${api.terms.url:}")
    String projectTermsUrl;

    @Value("${api.license.name:Apache License 2.0}")
    String projectLicenseName;

    @Value("${api.license.url:http://www.apache.org/licenses/LICENSE-2.0}")
    String projectLicenseUrl;


    @Bean
    Docket api() {
        return createApi("default", "REST", "/(api)/.*");
    }

    @Bean
    Docket dependencies() {
        return createApi("dependencies", "Dependencies'", "/(mock)/.*");
    }

    Docket createApi(String groupName, String apiName, String pathRegex) {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(groovy.lang.MetaClass.class)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(pathRegex))
                .build()
                .apiInfo(apiInfo(apiName));
    }


    ApiInfo apiInfo(String apiName) {
        return new ApiInfo(
                projectName + " " + apiName + " API",
                projectDescription,
                projectVersion,
                projectTermsUrl,
                null,
                projectLicenseName,
                projectLicenseUrl,
                new ArrayList<VendorExtension>()
        );
    }
}