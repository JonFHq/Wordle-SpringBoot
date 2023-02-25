package wordle.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Word {

    private List<Letter> letters = new ArrayList<Letter>();

    public Word() {
    }

    public Word(List<Letter> letters) {
        this.letters = letters;
    }

    public void addLetter(Letter letter) {
        letters.add(letter);
    }

    public void addLetters(List<Letter> letters) {
        this.letters.addAll(letters);
    }

    public void removeLetter(Letter letter) {
        letters.remove(letter);
    }

    public void removeLetters(List<Letter> letters) {
        this.letters.removeAll(letters);
    }

    public void clear() {
        letters.clear();
    }

    public int size() {
        return letters.size();
    }

    public Letter get(int index) {
        return letters.get(index);
    }

    public boolean contains(Letter letter) {
        return letters.contains(letter);
    }
}
