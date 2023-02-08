package wordle.service;

import wordle.model.Letter;
import wordle.model.Word;

public interface IWordleService {

    public String checkLetter(Letter letter, char[] word);

    public Word getWord(String word);

    public void wordle(String word);

}
