package wordle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import wordle.model.Letter;
import wordle.model.Word;
import wordle.model.Wordle;
import wordle.repository.IWordleRepository;
import wordle.service.WordleService;

@SpringBootTest
class WordleServiceTest {

    @Mock
    private IWordleRepository wordleRepository;

    @InjectMocks
    private WordleService wordleService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWord() {
        String wordStr = "test";
        Character[] wordArr = { 't', 'e', 's', 't' };
        Word word = wordleService.getWord(wordStr);
        for (int i = 0; i < wordArr.length; i++) {
            assertEquals(wordArr[i], word.get(i).getLetter());
        }
    }

    @Test
    void testCheckWordCorrect() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(wordArr[i], i);
            word.addLetter(letter);
        }

        Character[] guessArr = { 't', 'e', 's', 't' };
        Word guess = new Word();
        for (int i = 0; i < guessArr.length; i++) {
            Letter letter = new Letter(guessArr[i], i);
            guess.addLetter(letter);
        }

        List<Word> words = new ArrayList<Word>();
        words.add(guess);

        Wordle wordle = new Wordle(wordArr, 10, words);
        wordle.setVictory(false);

        wordleService.checkWord(guess, wordle.getWord());

        assertTrue(wordle.isVictory());
    }

    @Test
    void testCheckWordIncorrect() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(wordArr[i], i);
            word.addLetter(letter);
        }

        Character[] guessArr = { 't', 'e', 's', 'x' };
        Word guess = new Word();
        for (int i = 0; i < guessArr.length; i++) {
            Letter letter = new Letter(guessArr[i], i);
            guess.addLetter(letter);
        }

        List<Word> words = new ArrayList<Word>();
        words.add(guess);

        Wordle wordle = new Wordle(wordArr, 10, words);
        wordle.setVictory(false);

        wordleService.checkWord(guess, wordle.getWord());

        assertFalse(wordle.isVictory());
    }

    @Test
    void testWordle() {
        // Set up initial state
        String wordStr = "test";
        Character[] wordArr = { 't', 'e', 's', 't' };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(wordArr[i], i);
            word.addLetter(letter);
        }
        List<Word> words = new ArrayList<Word>();
        Wordle wordle = new Wordle(wordArr, 10, words);
        wordle.setVictory(false);

        // Call method under test
        wordleService.wordle(wordStr);

        // Check post-conditions
        assertTrue(wordle.getWords().contains(word));
        assertTrue(wordle.isVictory());
    }

    @Test
    void testGetWordle() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        List<Word> words = new ArrayList<Word>();
        Wordle wordle = new Wordle(wordArr, 10, words);

        when(wordleRepository.getWordle()).thenReturn(wordle);

        Wordle result = wordleService.getWordle();

        assertEquals(wordle, result);
    }

    @Test
    void testResetWordle() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        List<Word> words = new ArrayList<Word>();
        Wordle wordle = new Wordle(wordArr, 10, words);

        when(wordleRepository.getWordle()).thenReturn(wordle);

        // add a word to the wordle to make sure it's reset
        Word myWord = new Word();
        myWord.addLetter(new Letter('t', 0));
        myWord.addLetter(new Letter('e', 1));
        myWord.addLetter(new Letter('s', 2));
        myWord.addLetter(new Letter('t', 3));
        wordle.addWord(myWord);
        wordle.setVictory(true);

        // call the reset function
        wordleService.resetWordle();

        // check that the wordle is reset
        Wordle result = wordleService.getWordle();
        assertFalse(result.isVictory());
        assertTrue(result.getWords().isEmpty());
    }

}
