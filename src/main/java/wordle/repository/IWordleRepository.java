package wordle.repository;

import wordle.model.Word;
import wordle.model.Wordle;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public interface IWordleRepository {

    void reset();

    Wordle getWordle();
}
