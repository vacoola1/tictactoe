package testask.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Move {
    private Integer gameId;
    private Integer id;
    private Integer cell;

    public boolean isX() {
        return this.id % 2 == 1;
    }

    public boolean isO() {
        return this.id % 2 == 0;
    }

}
