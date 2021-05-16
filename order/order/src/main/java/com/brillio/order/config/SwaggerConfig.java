package com.brillio.order.config;

import java.util.HashSet;

import java.util.Set;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;

import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

@EnableSwagger2

public class SwaggerConfig {

	@Bean

	public Docket productApi() {

		Set<String> responseProduceType = new HashSet<String>();

		responseProduceType.add("application/json");

		responseProduceType.add("application/xml");
		
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.brillio.order"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {

		@SuppressWarnings("deprecation")

		ApiInfo apiInfo = new ApiInfo(

				"Fuze Product App",

				"All api related information",

				"API",

				"Terms of services",

				"vidhi.verma@brillio.com, pinakshi.sahu@brillio.com",

				"License of API",

				"API License URL");

		return apiInfo;

	}

}
