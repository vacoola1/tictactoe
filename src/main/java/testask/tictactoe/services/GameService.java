package testask.tictactoe.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.GameStatus;

import java.util.List;

@Service
public interface GameService {
    Mono<List<Game>> findAll();
    Mono<Game> find(Integer id);
    Mono<Game> findInProgress(Integer id);
    Mono<Game> create(Game game);
    Mono<Game> update(Game game);
    Mono<Game> updateStatus(Integer gameId, GameStatus status);
    Mono<Boolean> delete(Integer id);
}
