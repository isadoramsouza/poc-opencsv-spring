package com.isadora.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PocOpencsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocOpencsvApplication.class, args);
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.isadora.poc"))
				.build().apiInfo(apiInfo()).tags(new Tag("tag1", "Documentação da API POC OpenCSV"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("POC OpenCSV API").version("01.00.00").build();
	}


}
