package testask.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
    private Integer id;
    private String name;
    private GameStatus status;

}
