package testask.tictactoe.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Move;

import java.util.List;

@Repository
public interface MoveRepository {
    Mono<List<Move>> findMovesSorted(Integer gameId);
    Mono<Move> create(Move move);
}
