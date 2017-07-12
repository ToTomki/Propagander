package pl.tomaszkubicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = ArticleRepository.class)
//@EnableJpaRepositories(basePackages="org.bluedolphin.spring.data.mysql")
public class PropaganderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropaganderApplication.class, args);
	}
}
