package wordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("Wordle.*")
public class WordleApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WordleApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}

}
