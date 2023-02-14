package wordle.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Letter {
    
    private Character letter;
    private int position;
    private String color;

    public Letter() {
    }

    public Letter(Character letter, int position, String color) {
        this.letter = letter;
        this.position = position;
        this.color = color;
    }
}
