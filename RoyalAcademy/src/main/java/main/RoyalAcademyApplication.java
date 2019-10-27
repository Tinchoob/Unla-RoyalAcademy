package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"controladores"})
@EnableJpaRepositories(basePackages = {"abm"})
@EntityScan("datos")
public class RoyalAcademyApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RoyalAcademyApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RoyalAcademyApplication.class, args);
	}

}
