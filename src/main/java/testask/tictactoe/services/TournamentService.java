package testask.tictactoe.services;

import testask.tictactoe.model.Turnament;

import java.util.List;

public interface TournamentService {

    List<Turnament> getUserTurnaments(String userId);
}
