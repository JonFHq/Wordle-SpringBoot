package wordle.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import wordle.model.Letter;
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
    static int Maxtries = 20;

    private Wordle wordle;

    @BeforeEach
    void setUp() {
        wordle = new Wordle(word, Maxtries, words);
        when(wordleService.getWordle()).thenReturn(wordle);
    }

    @AfterEach
    void tearDown() {
        wordle.getWords().clear();
    }

    @Test
    void testIndex() {
        String mv = wordleController.index();
        assertEquals("index", mv);
    }

    @Test
    void testWordleEmpty() {
        ModelAndView mv = wordleController.wordle(new ModelAndView());
        assertEquals(false, mv.getModel().get("gameOver"));

        this.wordle.setVictory(true);
        mv = wordleController.wordle(new ModelAndView());
        assertEquals(true, mv.getModel().get("victory"));

        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testWordleFull() {
        ModelAndView mv = wordleController.wordle(new ModelAndView());
        for (int i = 0; i < Maxtries+2; i++) {
            wordleService.getWordle().addWord(new Word());
        }
        mv = wordleController.wordle(new ModelAndView());
        assertEquals(true, mv.getModel().get("gameOver"));

        this.wordle.setVictory(true);
        mv = wordleController.wordle(new ModelAndView());
        assertEquals(true, mv.getModel().get("victory"));

        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testWordlePostEmpty() {
        ModelAndView mv = wordleController.wordlePost("test", new ModelAndView());
        assertEquals(false, mv.getModel().get("gameOver"));

        wordleService.getWordle().setVictory(true);
        mv = wordleController.wordlePost("test", new ModelAndView());
        assertEquals(true, mv.getModel().get("victory"));

        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testWordlePostFull() {
        ModelAndView mv = wordleController.wordlePost("pool", new ModelAndView());
        for (int i = 0; i < Maxtries; i++) {
            wordleService.getWordle().addWord(new Word());
        }
        mv = wordleController.wordlePost("pool", new ModelAndView());
        assertEquals(true, mv.getModel().get("gameOver"));

        assertEquals("wordle", mv.getViewName());
        assertEquals(wordle, mv.getModel().get("wordle"));
    }

    @Test
    void testSearchTryEmpty() {
        ModelAndView mv = wordleController.searchTry(new ModelAndView());
        assertEquals(0, mv.getModel().get("tries"));
        assertEquals("searchTry", mv.getViewName());
    }

    @Test
    void testSearchTryFull() {
        ModelAndView mv = wordleController.searchTry(new ModelAndView());

        List<Word> words = new ArrayList<Word>();
        List<Letter> letters = new ArrayList<Letter>();
        Character[] word = {'T', 'E', 'S', 'T'};
        int tries = 10;
        for(int i = 0; i < word.length; i++) {
            letters.add(new Letter(word[i], i));
        }
        for(int i = 0; i < tries; i++) {
            words.add(new Word());
            words.get(i).setLetters(letters);
        }

        this.wordle.getWords().addAll(words);
        mv = wordleController.searchTry(new ModelAndView());

        assertEquals("searchTry", mv.getViewName());
        assertEquals(tries, mv.getModel().get("tries"));
    }

    @Test
    void testSearchTryPostEmpty() {
        ModelAndView mv = wordleController.searchTryPost(1, new ModelAndView());
        assertEquals(0, mv.getModel().get("tries"));
        assertEquals(1, mv.getModel().get("try"));
    }

    @Test
    void testSearchTryPostFull() {
        List<Word> words = new ArrayList<Word>();
        List<Letter> letters = new ArrayList<Letter>();
        Character[] word = {'T', 'E', 'S', 'T'};
        int tries = 10;
        for(int i = 0; i < word.length; i++) {
            letters.add(new Letter(word[i], i));
        }
        for(int i = 0; i < tries; i++) {
            words.add(new Word());
            words.get(i).setLetters(letters);
        }

        this.wordle.getWords().addAll(words);
        ModelAndView mv = wordleController.searchTryPost(2, new ModelAndView());

        assertEquals("searchTry", mv.getViewName());
        assertEquals(tries, mv.getModel().get("tries"));
        assertEquals(2, mv.getModel().get("try"));
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