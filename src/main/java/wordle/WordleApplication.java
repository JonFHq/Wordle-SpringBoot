package wordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("wordle.*")
public class WordleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}

}
