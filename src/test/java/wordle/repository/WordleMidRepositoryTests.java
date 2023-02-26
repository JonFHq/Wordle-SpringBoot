package wordle.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wordle.model.Wordle;

@ExtendWith(MockitoExtension.class)
public class WordleMidRepositoryTests {

    @Mock
    private Wordle wordleMock;

    @InjectMocks
    private WordleMidRepository repository;

    @Test
    public void testGetWordle() {
        Wordle result = repository.getWordle();

        assertSame(wordleMock, result);
    }
}
