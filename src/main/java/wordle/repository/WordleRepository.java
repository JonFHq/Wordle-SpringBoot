package wordle.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wordle.model.Word;
import wordle.model.Wordle;

import lombok.Getter;
import lombok.Setter;

@Repository
public class WordleRepository implements IWordleRepository {

    private static Wordle wordle = new Wordle();

    @Override
    public void reset() {
        wordle = new Wordle();
    }

    @Override
    public Wordle getWordle() {
        return wordle;
    }
}
