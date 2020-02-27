package com.alllexe.phonebook;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class PhonebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {

		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth("basicAuth"));

		return new Docket(DocumentationType.SWAGGER_2)
				.ignoredParameterTypes(AuthenticationPrincipal.class)
				.select()
				.paths(PathSelectors.ant("/api/contacts/*"))
				.apis(RequestHandlerSelectors.basePackage("com.alllexe.phonebook"))
				.build()
				.apiInfo(apiDetails())
				.securitySchemes(schemeList);
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Address book api",
				"Sample api for address book",
				"1.0",
				"free for use",
				new Contact("Alexander Abramov", "", "alllexe@mail.ru"),
				"API license",
				"",
				Collections.emptyList()
		);
	}

}
