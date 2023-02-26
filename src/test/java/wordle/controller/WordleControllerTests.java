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

    private Wordle wordle;

    @BeforeEach
    void setUp() {
        wordle = new Wordle(word, tries, words);
        when(wordleService.getWordle()).thenReturn(wordle);
    }

    @Test
    void testIndex() {
        String mv = wordleController.index();
        assertEquals("index", mv);
    }

    @Test
    void testWordle() {
        ModelAndView mv = wordleController.wordle(new ModelAndView());
        assertEquals(false, mv.getModel().get("gameOver"));
        for (int i = 0; i < tries+2; i++) {
            wordleService.getWordle().addWord(new Word());
        }
        mv = wordleController.wordle(new ModelAndView());
        assertEquals(true, mv.getModel().get("gameOver"));
        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testWordlePost() {
        ModelAndView mv = wordleController.wordlePost("test", new ModelAndView());
        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testSearchTry() {
        ModelAndView mv = wordleController.searchTry(new ModelAndView());
        assertEquals("searchTry", mv.getViewName());
        assertEquals(0, mv.getModel().get("tries"));
    }

    @Test
    void testSearchTryPost() {
        ModelAndView mv = wordleController.searchTryPost(1, new ModelAndView());
        assertEquals("searchTry", mv.getViewName());
        assertEquals(0, mv.getModel().get("tries"));
    }

    @Test
    void testCheckResetGet() {
        String mv = wordleController.checkResetGet();
        assertEquals("redirect:/wordle", mv);
    }

    @Test
    void testCheckResetPost() {
        String mv = wordleController.checkResetPost();
        assertEquals("redirect:/wordle", mv);
    }
}