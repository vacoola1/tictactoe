package testask.tictactoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testask.tictactoe.model.Tournament;
import testask.tictactoe.repository.TournamentRepository;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {
    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> getUserTournaments(Integer userId) {
        return tournamentRepository.findUserTournaments(userId);
    }
}
