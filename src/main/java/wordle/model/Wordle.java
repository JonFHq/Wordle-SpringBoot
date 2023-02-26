package wordle.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Wordle {

    private Character[] word;
    private List<Word> words;
    private boolean victory;

    private static int tries;

    public Wordle() {
    }

    public Wordle(Character[] word, int tries, List<Word> words) {
        this.word = word;
        Wordle.tries = tries;
        this.words = words;
        this.victory = false;
    }

    public Character[] getWord() {
        return word;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public int getTries() {
        return tries;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

}
