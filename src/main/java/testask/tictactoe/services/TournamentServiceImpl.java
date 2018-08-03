package testask.tictactoe.services;

import org.springframework.stereotype.Service;
import testask.tictactoe.model.Turnament;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {
    @Override
    public List<Turnament> getUserTurnaments(String userId) {
        return null;
    }
}
