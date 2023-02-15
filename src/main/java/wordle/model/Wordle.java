package wordle.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Wordle {

    private Character[] word;
    private static int wordLength;
    private List<Word> words;
    private int tries;

    public Wordle() {
    }

    public Wordle(Character[] word, int tries, List<Word> words) {
        this.word = word;
        wordLength = word.length;
        this.tries = tries;
        this.words = words;
    }

    public Character[] getWord() {
        return word;
    }

    public void setWord(Character[] word) {
        this.word = word;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public int getWordLength() {
        return wordLength;
    }

}
