package testask.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Move {
    private Integer gameId;
    private Integer id;
    private Integer cell;

    @JsonIgnore
    public boolean isX() {
        return this.id % 2 == 1;
    }

    @JsonIgnore
    public boolean isO() {
        return this.id % 2 == 0;
    }

}
