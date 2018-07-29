package testask.tictactoe.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Move;

import java.util.List;

@Service
public interface MoveService {
    Mono<Move> create(Move move);
    Mono<List<Move>> findGameMovesSorted(Integer gameId);
}
