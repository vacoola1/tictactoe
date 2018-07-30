package testask.tictactoe.repository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MoveRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, MoveRowMapper moveRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.moveRowMapper = moveRowMapper;
    }

    @Override
    public Mono<List<Move>> findMovesSorted(Integer gameId) {
        var query = "SELECT GAME_ID, ID, CELL FROM MOVE WHERE GAME_ID=:gameId";
        return supplyAsync(() -> jdbcTemplate.query(query, Map.of("gameId", gameId), moveRowMapper));
    }

    @Override
    public Mono<Move> create(Move move) {
        var query = "INSERT INTO MOVE (GAME_ID, ID, CELL) VALUES (:gameId, :id, :cell)";
        var params = Map.of(
                "gameId", move.getGameId(),
                "id", move.getId(),
                "cell", move.getCell());

        return supplyAsync(() -> jdbcTemplate.update(query, params))
                .map(id -> move);
    }
}
