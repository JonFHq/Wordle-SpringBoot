package wordle;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WordleSpringBootApplicationTests {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    public void testConfigure() {
        WordleApplication app = new WordleApplication();
        Mockito.when(springApplicationBuilder.sources(WordleApplication.class)).thenReturn(springApplicationBuilder);
        SpringApplicationBuilder result = app.configure(springApplicationBuilder);
        Mockito.verify(springApplicationBuilder).sources(WordleApplication.class);
        assertEquals(springApplicationBuilder, result);
    }

    @Test
    void contextLoads() {
        //WordleApplication.main(new String[] {});
		assertTrue(true);
    }

}
