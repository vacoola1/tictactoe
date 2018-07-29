package testask.tictactoe.repository;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import testask.tictactoe.model.Move;

import java.sql.ResultSet;

@Component
public class MoveRowMapper implements RowMapper<Move> {

    @Override
    @SneakyThrows
    public Move mapRow(ResultSet rs, int rowNum) {
        return Move.of(
                rs.getInt("GAME_ID"),
                rs.getInt("ID"),
                rs.getInt("CELL"));
    }

}
