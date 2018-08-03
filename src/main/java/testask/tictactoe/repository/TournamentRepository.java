package testask.tictactoe.repository;

import testask.tictactoe.model.Tournament;

import java.util.List;

public interface TournamentRepository {
    List<Tournament> findUserTournaments(Integer userId);
}
