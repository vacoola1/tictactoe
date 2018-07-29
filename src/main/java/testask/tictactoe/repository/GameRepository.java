package testask.tictactoe.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;

import java.util.List;

@Repository
public interface GameRepository {
    Mono<List<Game>> findAll();
    Mono<Game> find(Integer id);
    Mono<Game> create(Game game);
    Mono<Game> update(Game game);
    Mono<Boolean> delete(Integer id);
}
