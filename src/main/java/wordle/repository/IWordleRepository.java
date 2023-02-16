package wordle.repository;

import wordle.model.Wordle;

public interface IWordleRepository {

    void reset();

    Wordle getWordle();
    
}
