package testask.tictactoe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import testask.tictactoe.model.Turnament;

import java.util.List;
import java.util.Map;

@Repository
public class TurnamentRepositoryImpl implements TurnamentRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnamentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<TurnamentRepository> findUserTurnaments(String userId) {
        var query = "SELECT ID, NAME FROM TOURNAMENT LEFT JOIN VOTES ON TOURNAMENT.ID=VOTES.TOURNAMENT_ID AND VOTES.USER_ID = :userId " +
                "WHERE (VOTES.CHOICE IS NULL) OR VOTES.CHOICE";
        return jdbcTemplate.queryForObject(query, Map.of("userId", userId), ContentRowMapper.of(objectMapper, Turnament.class));
    }
}
