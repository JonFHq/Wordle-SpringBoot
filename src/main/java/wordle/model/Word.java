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

    public void addLetter(Letter letter) {
        letters.add(letter);
    }

}
