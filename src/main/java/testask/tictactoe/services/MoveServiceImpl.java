package testask.tictactoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Move;
import testask.tictactoe.repository.MoveRepository;

import java.util.List;

@Service
public class MoveServiceImpl implements MoveService {

    private MoveRepository moveRepository;

    @Autowired
    public MoveServiceImpl(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    @Override
    public Mono<List<Move>> findGameMovesSorted(Integer gameId) {
        return moveRepository.findMovesSorted(gameId);
    }

    @Override
    public Mono<Move> create(Move move) {
        return moveRepository.create(move);
    }

}
