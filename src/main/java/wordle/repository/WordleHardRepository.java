package wordle.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.model.Word;
import wordle.model.Wordle;

@Profile("test")
@Repository
public class WordleHardRepository implements IWordleRepository {

    static Character[] word = {'A', 'B', 'C', 'D', 'E', 'D', 'C', 'B', 'A'};
    static List<Word> words = new ArrayList<Word>();
    static int tries = 6;

    private static Wordle wordle = new Wordle(word, tries, words);

    @Override
    public void reset() {
        wordle = new Wordle();
    }

    @Override
    public Wordle getWordle() {
        return wordle;
    }
}
