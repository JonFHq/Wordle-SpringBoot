package wordle.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import wordle.model.Word;
import wordle.model.Wordle;
import wordle.service.IWordleService;

@SpringBootTest
class WordleControllerTests {

    @Mock
    IWordleService wordleService;

    @InjectMocks
    WordleController wordleController;

    static Character[] word = {'T', 'E', 'S', 'T'};
    static List<Word> words = new ArrayList<Word>();
    static int tries = 20;

    private Wordle wordle = new Wordle(word, tries, words);

    @BeforeEach
    void setUp() {
        wordle = new Wordle();
        when(wordleService.getWordle()).thenReturn(wordle);
    }

    @Test
    void testIndex() {
        String modelAndView = wordleController.index();
        assertEquals("index", modelAndView);
    }

}