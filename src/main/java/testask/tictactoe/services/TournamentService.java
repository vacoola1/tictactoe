package testask.tictactoe.services;

import testask.tictactoe.model.Tournament;

import java.util.List;

public interface TournamentService {

    List<Tournament> getUserTournaments(Integer userId);
}
