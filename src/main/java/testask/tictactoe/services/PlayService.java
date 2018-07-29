package testask.tictactoe.services;

import reactor.core.publisher.Mono;
import testask.tictactoe.model.GameStatus;

public interface PlayService {
    Mono<GameStatus> doNextMove(Integer gameId, Integer cell);
}
