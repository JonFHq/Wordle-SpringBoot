package wordle.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wordle.model.Word;
import wordle.model.Wordle;

@Repository
public class WordleRepository implements IWordleRepository {

    static Character[] word = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
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
