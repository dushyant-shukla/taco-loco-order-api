package com.taco.loco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taco.loco.domain.MenuItemModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;


@SpringBootApplication
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.taco.loco.controller"))
//				.paths(PathSelectors.any())
				.paths(regex("/order.*"))
				.build()
				.apiInfo(new ApiInfo(
						"REST API",
						"Order Processing API for TacoLoco.",
						"0.0.1",
						"",
						new Contact("Dushyant Shukla", "", "dushyantshukla@outlook.com"),
						"", "", Collections.emptyList()));
	}

}
