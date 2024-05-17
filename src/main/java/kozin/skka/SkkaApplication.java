package kozin.skka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SkkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkkaApplication.class, args);
	}

}
