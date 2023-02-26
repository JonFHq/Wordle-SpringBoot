package wordle.service;

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

@SpringBootTest
class WordleServiceTest {

    @Mock
    private IWordleRepository wordleRepository;

    @InjectMocks
    private WordleService wordleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWord() {
        String wordStr = "test";
        Character[] wordArr = { 'T', 'E', 'S', 'T' };
        Word word = wordleService.getWord(wordStr);
        for (int i = 0; i < wordArr.length; i++) {
            assertEquals(wordArr[i], word.getLetters().get(i).getLetter());
        }
    }

    @Test
    void testCheckWordCorrect() {
        Character[] wordArr = { 'T', 'E', 'S', 'T' };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(wordArr[i], i);
            word.addLetter(letter);
        }

        Character[] guessArr = { 'T', 'E', 'S', 'T' };
        Word guess = new Word();
        for (int i = 0; i < guessArr.length; i++) {
            Letter letter = new Letter(guessArr[i], i);
            guess.addLetter(letter);
        }

        List<Word> words = new ArrayList<>();
        words.add(guess);

        Wordle wordle = new Wordle(wordArr, 10, words);

        wordleService.checkWord(guess, wordle.getWord(), wordle);

        assertTrue(wordle.isVictory());
    }

    @Test
    void testCheckWordIncorrectPosition() {
        Character[] wordArr = { 'T', 'E', 'S', 'T' };
        Character[] guessArr = { 'T', 'T', 'T', 'X' };

        String[] guessColor = { "bg-success", "bg-warning", "bg-danger", "bg-danger" };
        int[] guessPosition = { 0, 3, -1, -1 };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(guessArr[i], guessPosition[i]);
            letter.setColor(guessColor[i]);
            word.addLetter(letter);
        }

        Word guess = new Word();
        for (int i = 0; i < guessArr.length; i++) {
            Letter letter = new Letter(guessArr[i], -1);
            guess.addLetter(letter);
        }

        List<Word> words = new ArrayList<>();
        words.add(guess);

        Wordle wordle = new Wordle(wordArr, 10, words);

        wordleService.checkWord(guess, wordle.getWord(), wordle);

        for (int i = 0; i < word.getLetters().size(); i++) {
            assertEquals(word.getLetters().get(i).getLetter(), wordle.getWords().get(0).getLetters().get(i).getLetter());
        }

        for (int i = 0; i < word.getLetters().size(); i++) {
            assertEquals(word.getLetters().get(i).getPosition(), wordle.getWords().get(0).getLetters().get(i).getPosition());
        }

        assertFalse(wordle.isVictory());
    }

    @Test
    void testWordle() {
        String wordStr = "test";
        Character[] wordArr = { 'T', 'E', 'S', 'T' };
        Word word = new Word();
        for (int i = 0; i < wordArr.length; i++) {
            Letter letter = new Letter(wordArr[i], i);
            letter.setColor("bg-success");
            word.addLetter(letter);
        }
        List<Word> words = new ArrayList<>();
        Wordle wordle = new Wordle(wordArr, 10, words);
        wordle.setVictory(false);

        wordleService.wordle(wordStr, wordle);

        // for that shows the letters of word and the word generated in the wordle are the same letters
        for (int i = 0; i < word.getLetters().size(); i++) {
            assertEquals(word.getLetters().get(i).getLetter(), wordle.getWords().get(0).getLetters().get(i).getLetter());
        }

        assertTrue(wordle.isVictory());
    }

    @Test
    void testGetWordle() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        List<Word> words = new ArrayList<>();
        Wordle wordle = new Wordle(wordArr, 10, words);

        when(wordleRepository.getWordle()).thenReturn(wordle);

        Wordle result = wordleService.getWordle();

        assertEquals(wordle, result);
    }

    @Test
    void testResetWordle() {
        Character[] wordArr = { 't', 'e', 's', 't' };
        List<Word> words = new ArrayList<>();
        Wordle wordle = new Wordle(wordArr, 10, words);

        when(wordleRepository.getWordle()).thenReturn(wordle);

        Word myWord = new Word();
        myWord.addLetter(new Letter('t', 0));
        myWord.addLetter(new Letter('e', 1));
        myWord.addLetter(new Letter('s', 2));
        myWord.addLetter(new Letter('t', 3));
        wordle.addWord(myWord);
        wordle.setVictory(true);

        wordleService.resetWordle();

        Wordle result = wordleService.getWordle();
        assertFalse(result.isVictory());
        assertTrue(result.getWords().isEmpty());
    }

}
