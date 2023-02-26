package wordle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WordleApplicationTests {
	
    @Autowired
    private WordleApplication application;

    @Test
    void contextLoads() {
        assertNotNull(application);
    }

    @Test
    void mainTest() {
        WordleApplication.main(new String[] {});
    }

}
