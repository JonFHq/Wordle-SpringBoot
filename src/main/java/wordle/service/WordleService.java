package wordle.service;

import java.util.List;

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
            letter.setLetter(Character.toUpperCase(letters[i]));
            letter.setPosition(-1);
            result.getLetters().add(letter);
        }
        return result;
    }
    
    @Override
    public void checkWord(Word myWord, Character[] word) {
        List<Letter> letters = myWord.getLetters();
        for (int i = 0; i < letters.size(); i++) {
            if (letters.get(i).getLetter() == word[i]) {
                letters.get(i).setColor("bg-success");
                letters.get(i).setPosition(i);
            }
        }
        for (int i = 0; i < letters.size(); i++) {
            for (int j = 0; j < word.length; j++) {
                if (letters.get(i).getLetter() == word[j]
                        && letters.get(j).getColor() != "bg-success"
                        && letters.get(i).getColor() != "bg-success") {
                            boolean isRepeated = false;
                            for (int k = 0; k < letters.size(); k++) {
                                if (letters.get(k).getPosition() == j) {
                                    isRepeated = true;
                                }
                            }
                            if (!isRepeated) {
                                letters.get(i).setColor("bg-warning");
                                letters.get(i).setPosition(j);
                            }
                }
            }
            if (letters.get(i).getColor() == null) {
                letters.get(i).setColor("bg-danger");
            }
        }
    }

    @Override
    public void wordle(String word) {
        Wordle wordle = wordleRepository.getWordle();
        Character[] wordCheck = wordle.getWord();
        Word myWord = getWord(word);
        checkWord(myWord, wordCheck);
        // Change color of the letter cell
        wordle.addWord(myWord);
    }

    @Override
    public Wordle getWordle() {
        return wordleRepository.getWordle();
    }
}
