package wordle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordle.model.Letter;
import wordle.model.Word;
import wordle.model.Wordle;
import wordle.repository.IWordleRepository;

@Service
public class WordleService implements IWordleService {
    
    @Autowired
    private IWordleRepository wordleRepository;

    @Override
    public Word getWord(String word) {
        Word result = new Word();
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            Letter letter = new Letter();
            letter.setLetter(letters[i]);
            letter.setPosition(i+1);
            result.getLetters().add(letter);
        }
        return result;
    }
    
    @Override
    public String checkLetter(Letter letter, char[] word) {
        String result = "red";
        for (int i = 0; i < word.length; i++) {
            if (word[i] == letter.getLetter()) {
                result = "yellow";
                if ((i+1) == letter.getPosition())
                    result = "green";
            }
        }
        return result;
    }

    @Override
    public void wordle(String word) {
        Wordle wordle = wordleRepository.getWordle();
        wordle.
    }
}
