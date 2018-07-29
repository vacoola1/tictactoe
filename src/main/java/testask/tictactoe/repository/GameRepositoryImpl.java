package testask.tictactoe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;

import java.util.List;
import java.util.Map;

@Repository
public class GameRepositoryImpl extends AsyncRepository implements GameRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private GameRowMapper gameRowMapper;
    private SimpleJdbcInsert insertGame;

    @Autowired
    public GameRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, GameRowMapper gameRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.gameRowMapper = gameRowMapper;
        this.insertGame = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate().getDataSource())
                .withTableName("GAME")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public Mono<List<Game>> findAll() {
        var query = "SELECT ID, NAME, STATUS FROM GAME";

        return supplyAsync(() -> jdbcTemplate.query(query, gameRowMapper));
    }

    @Override
    public Mono<Game> find(Integer id) {
        var query = "SELECT ID, NAME, STATUS FROM GAME WHERE ID = :id";

        return supplyAsync(() -> jdbcTemplate.queryForObject(query, Map.of("id", id), gameRowMapper));
    }

    @Override
    public Mono<Game> create(Game game) {
        var params = new MapSqlParameterSource()
                .addValue("name", game.getName())
                .addValue("status", game.getStatus());

        return supplyAsync(() -> {
            Number newId = insertGame.executeAndReturnKey(params);
            game.setId(newId.intValue());
            return game;
        });
    }

    @Override
    public Mono<Game> update(Game game) {
        var query = "UPDATE GAME SET ID=:id, NAME=:name, STATUS=:status WHERE ID=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", game.getId())
                .addValue("name", game.getName())
                .addValue("status", game.getStatus());

        return supplyAsync(() -> jdbcTemplate.update(query, params))
                .map(id -> game);
    }

    @Override
    public Mono<Boolean> delete(Integer id) {
        var query = "DELETE FROM GAME WHERE ID=:id";

        return supplyAsync(() -> jdbcTemplate.update(query, Map.of("id", id)))
                .map(count -> count > 0);
    }
}
