package testask.tictactoe.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import testask.tictactoe.model.Game;
import testask.tictactoe.model.GameStatus;
import testask.tictactoe.model.Move;

import java.util.List;
import java.util.stream.Collectors;

import static testask.tictactoe.model.GameStatus.*;

@Service
public class PlayServiceImpl implements PlayService {
    private static final int SIDE_LENGTH = 3;
    private static final int AREA_SIZE = SIDE_LENGTH * SIDE_LENGTH; //3*3
    private static final int MIN_CELLS_TO_WIN = SIDE_LENGTH * 2 - 1; //3+2
    private static final List<List<Integer>> LINES = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6),
            List.of(7, 8, 9),
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(3, 6, 9),
            List.of(1, 5, 9),
            List.of(3, 5, 7)
    );

    private MoveService moveService;
    private GameService gameService;

    @Autowired
    public PlayServiceImpl(GameService gameService, MoveService moveService) {
        this.gameService = gameService;
        this.moveService = moveService;
    }

    @Override
    @SneakyThrows
    public Mono<GameStatus> doNextMove(Integer gameId, Integer cell) {
        return gameService.findInProgress(gameId)
                .zipWith(moveService.findGameMovesSorted(gameId))
                .flatMap(tuples -> {
                    var game = tuples.getT1();
                    var moves = tuples.getT2();
                    if (!isCorrectMove(moves, cell)) {
                        throw new RuntimeException("Incorrect move");
                    }
                    Move newMove = Move.of(gameId, moves.size() + 1, cell);
                    return moveService.create(newMove).flatMap(move -> {
                        moves.add(move);
                        return updateGameStatus(game, moves);
                    });
                });
    }

    private Mono<GameStatus> updateGameStatus(Game game, List<Move> moves) {
        var newStatus = provideNewGameStatus(moves);
        if (newStatus != IN_PROGRESS) {
            return gameService.updateStatus(game.getId(), newStatus).map(Game::getStatus);
        }else {
            return Mono.just(IN_PROGRESS);
        }
    }

    private boolean isCorrectMove(List<Move> moves, Integer cell) {
        return moves.size() < AREA_SIZE
                && cell <= AREA_SIZE
                && moves.stream().noneMatch(move -> move.getCell().equals(cell));
    }

    private GameStatus provideNewGameStatus(List<Move> moves) {
        if (moves.size() < MIN_CELLS_TO_WIN) {
            return IN_PROGRESS;
        }
        for (List<Integer> line : LINES) {
            List<Move> lineMoves = getMovesForLine(line, moves);
            if (isXLine(line, lineMoves)) {
                return WON_X;
            } else if (isOLine(line, lineMoves)) {
                return WON_O;
            }
        }
        if (moves.size() == AREA_SIZE) {
            return DRAW;
        } else {
            return IN_PROGRESS;
        }
    }

    List<Move> getMovesForLine(List<Integer> line, List<Move> moves) {
        return moves.stream()
                .filter(move -> line.contains(move.getCell()))
                .collect(Collectors.toList());
    }

    private boolean isXLine(List<Integer> line, List<Move> movesForLine) {
        return line.stream().allMatch(lineCell ->
                movesForLine.size() == 3 && movesForLine.stream().allMatch(Move::isX)
        );
    }

    private boolean isOLine(List<Integer> line, List<Move> movesForLine) {
        return line.stream().allMatch(lineCell ->
                movesForLine.size() == 3 && movesForLine.stream().allMatch(Move::isO)
        );
    }

}
