package wordle.service;

import wordle.model.Word;
import wordle.model.Wordle;

public interface IWordleService {

    public void checkWord(Word myWord, Character[] word, Wordle wordle);

    public Word getWord(String word);

    public void wordle(String word, Wordle wordle);

    Wordle getWordle();

    void resetWordle();

}
