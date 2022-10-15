package com.example.configuration;

import com.example.configuration.properties.BootifulPropertySource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
@Log4j2
@SpringBootApplication
@PropertySource("classpath:application-foo.properties") 	// priorité 3 : après spring.profiles.active=dev et SpringApplicationBuilder.profiles("other")
public class ConfigurationApplication {

	public static void main(String[] args) {

		//SpringApplication.run(ConfigurationApplication.class, args);
			/*new SpringApplicationBuilder() 		// priorité 2: après spring.profiles.active=dev 1
				.profiles("foo")
				.sources(ConfigurationApplication.class)
				.run(args);*/
		new SpringApplicationBuilder()
				.sources(ConfigurationApplication.class)
				.initializers(context -> context
						.getEnvironment()
						.getPropertySources()
						.addLast(new BootifulPropertySource())
				)
				.run(args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment, ConfigurableEnvironment configurableEnvironment,
										@Value("${EMAIL_ADDRESS}") String emailAddress,
										@Value("${developer-name:Default Razafinirina}") String developerName,
										@Value("${developer-lastname:Default Dauphin}") String developerLastname,
										@Value("${bootiful-message}") String bootifulMessage) {
		return args -> {
			System.out.print("");
			System.out.println("												                                                    ------------------- environment --------------------------");
			log.info("environment developer-name: " + environment.getProperty("developer-name"));
			// EMAIL_ADDRESS: définit comme une variable d'environnment système
			log.info("environment email_address: " + environment.getProperty("EMAIL_ADDRESS"));
			log.info("environment bootiful-message: " + environment.getProperty("bootiful-message"));
			System.out.print("");

			System.out.println("												                                                    ------------------- configurableEnvironment --------------------------");
			log.info("configurableEnvironment developer-name: " + configurableEnvironment.getProperty("developer-name"));
			log.info("configurableEnvironment developper function: " + configurableEnvironment.getProperty("developer.function"));
			System.out.print("");
			System.out.println("												                                                    ------------------- Value --------------------------");
			log.info("Value developer-name @Value: " + developerName);
			log.info("Value developer-lastname @Value: " + developerLastname);
			log.info("Value email_address @Value: " + emailAddress);
			log.info("Value bootiful-message: " + bootifulMessage);

		};
	}

}
