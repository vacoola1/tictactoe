package testask.tictactoe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import testask.tictactoe.model.Tournament;
import testask.tictactoe.services.TournamentService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournaments/list/")
    List<Tournament> getUserTournaments(@RequestParam("userId") Integer userId) {
        return tournamentService.getUserTournaments(userId);
    }
}
