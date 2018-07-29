package testask.tictactoe.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.Move;
import testask.tictactoe.model.GameStatus;
import testask.tictactoe.services.GameService;
import testask.tictactoe.services.MoveService;
import testask.tictactoe.services.PlayService;

import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1")
public class GameController {
    private GameService gameService;
    private PlayService playService;
    private MoveService moveService;


    @Autowired
    public GameController(GameService gameService, PlayService playService, MoveService moveService) {
        this.gameService = gameService;
        this.playService = playService;
        this.moveService = moveService;
    }

    @GetMapping("/games")
    public Mono<List<Game>> getGames() {
        return gameService.findAll();
    }

    @GetMapping("/games/{gameId}")
    public Mono<Game> getGame(@PathVariable("gameId") Integer gameId) {
        return gameService.find(gameId);
    }

    @GetMapping("/games/{gameId}/moves")
    public Mono<List<Move>> getMoves(@PathVariable("gameId") Integer gameId) {
        return moveService.findGameMovesSorted(gameId);
    }

    @PostMapping("/games/{gameId}/moves/{cell}")
    public Mono<GameStatus> nextMove(@PathVariable("gameId") Integer gameId, @PathVariable("cell") Integer cell) {
        return playService.doNextMove(gameId, cell);
    }

}