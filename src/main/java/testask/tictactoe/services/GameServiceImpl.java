package testask.tictactoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.GameStatus;
import testask.tictactoe.repository.GameRepository;
import testask.tictactoe.repository.MoveRepository;

import java.util.List;
import java.util.Objects;

import static testask.tictactoe.model.GameStatus.IN_PROGRESS;

@Service
public class GameServiceImpl implements GameService {
    private static final String DEFAULT_GAME_NAME = "Tic tac Fight";

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
    public Mono<Game> findInProgress(Integer id) {
        return gameRepository.find(id)
                .onErrorMap(ex -> new RuntimeException("game is not found"))
                .map(game -> {
                    if (game.getStatus() != IN_PROGRESS) {
                        throw new RuntimeException("Live game is not found");
                    }
                    return game;
                });
    }

    @Override
    public Mono<Game> create(Game game) {
        if (Objects.equals(game.getStatus(), null)) {
            game.setStatus(IN_PROGRESS);
        }
        if (Objects.equals(game.getName(), null)) {
            game.setName(DEFAULT_GAME_NAME);
        }
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
