package testask.tictactoe.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Move;

import java.util.List;
import java.util.Map;

@Repository
public class MoveRepositoryImpl extends AsyncRepository implements MoveRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private MoveRowMapper moveRowMapper;

    @Override
    public Mono<List<Move>> findMovesSorted(Integer gameId) {
        var query = "SELECT GAME_ID, MOVE.ID AS ID, CELL FROM GAME LEFT JOIN MOVE ON GAME_ID=:gameId AND GAME.ID = MOVE.GAME_ID";
        return supplyAsync(() -> jdbcTemplate.query(query, Map.of("gameId", gameId), moveRowMapper));
    }

    @Override
    public Mono<Move> create(Move move) {
        var query = "INSERT INTO MOVE (GAME_ID, ID, CELL) VALUES (:groupId, :id, :cell)";
        var params = Map.of(
                "gameId", move.getGameId(),
                "id", move.getId(),
                "cell", move.getCell());

        return supplyAsync(() -> jdbcTemplate.update(query, params))
                .map(id -> move);
    }
}
