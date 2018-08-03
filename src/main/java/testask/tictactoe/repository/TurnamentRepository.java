package testask.tictactoe.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface TurnamentRepository {
    List<TurnamentRepository> findUserTurnaments(String userId);
}
