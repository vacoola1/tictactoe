package testask.tictactoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.GameStatus;
import testask.tictactoe.repository.GameRepository;
import testask.tictactoe.repository.MoveRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    @Autowired
    GameServiceImpl(GameRepository gameRepository, MoveRepository moveRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<List<Game>> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Mono<Game> find(Integer id) {
        return gameRepository.find(id);
    }

    @Override
    public Mono<Game> create(Game game) {
        return gameRepository.create(game);
    }

    @Override
    public Mono<Game> update(Game game) {
        return gameRepository.update(game);
    }

    @Override
    public Mono<Game> updateStatus(Integer gameId, GameStatus status) {
        return gameRepository.find(gameId).flatMap(game -> {
            game.setStatus(status);
            return gameRepository.update(game);
        });
    }

    @Override
    public Mono<Boolean> delete(Integer id) {
        return gameRepository.delete(id);
    }
}
