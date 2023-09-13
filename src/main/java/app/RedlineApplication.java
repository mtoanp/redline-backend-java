package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication
//@CrossOrigin(maxAge = 3600)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RedlineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("let's begin");
		System.out.println("============================================");
	}

}
