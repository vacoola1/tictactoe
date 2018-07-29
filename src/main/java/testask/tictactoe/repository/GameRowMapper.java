package testask.tictactoe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.GameStatus;

import java.sql.ResultSet;

@Component
public class GameRowMapper implements RowMapper<Game> {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public Game mapRow(ResultSet rs, int rowNum) {
        return new Game(
                rs.getInt("ID"),
                rs.getString("NAME"),
                GameStatus.valueOf(rs.getString("STATUS")));
    }

}
