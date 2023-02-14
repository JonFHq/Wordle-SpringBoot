package wordle.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.model.Word;
import wordle.model.Wordle;

@Profile("easy")
@Repository
public class WordleEasyRepository implements IWordleRepository {

    static Character[] word = {'A', 'B', 'C'};
    static List<Word> words = new ArrayList<Word>();
    static int tries = 20;

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
