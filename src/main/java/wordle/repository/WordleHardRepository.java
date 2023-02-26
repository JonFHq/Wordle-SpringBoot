package wordle.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.model.Word;
import wordle.model.Wordle;

@Repository
@Profile("hard")
public class WordleHardRepository implements IWordleRepository {

    static Character[] word = {'P', 'E', 'S', 'C', 'A', 'D', 'O', 'R'};
    static List<Word> words = new ArrayList<Word>();
    static int tries = 6;

    private Wordle wordle = new Wordle(word, tries, words);

    @Override
    public Wordle getWordle() {
        return this.wordle;
    }
}
