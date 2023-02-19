package wordle.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Wordle {

    private Character[] word;
    private List<Word> words;

    private static int wordLength;
    private static int maxTries;

    public Wordle() {
    }

    public Wordle(Character[] word, int tries, List<Word> words) {
        this.word = word;
        wordLength = word.length;
        maxTries = tries;
        maxTries = tries;
        this.words = words;
    }

    public Character[] getWord() {
        return word;
    }

    public void setWord(Character[] word) {
        this.word = word;
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

    public int getMaxTries() {
        return maxTries;
    }

}
