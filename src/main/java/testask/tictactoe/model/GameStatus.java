package testask.tictactoe.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GameStatus {
    IN_PROGRESS("In progress"),
    WON_O("X won"),
    WON_X("O won"),
    DRAW("O won");

    private String label;
}
