package wordle.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import wordle.repository.WordleRepository;

import java.util.ArrayList;

@Getter
@Setter
@Component
public class Wordle {

    private static char[] word = {'a', 'b', 'c'};
    private static int tries = 20;
    private static List<Word> words = new ArrayList<Word>();

    public Wordle() {
    }

    public Wordle(char[] word, int tries, List<Word> words) {
        this.word = word;
        this.tries = tries;
        this.words = words;
    }
}
