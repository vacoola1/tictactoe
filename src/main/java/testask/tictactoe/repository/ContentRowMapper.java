package testask.tictactoe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

@AllArgsConstructor(staticName = "of")
public class ContentRowMapper<T> implements RowMapper<T> {
    private ObjectMapper objectMapper;
    private Class<T> classType;

    @Override
    @SneakyThrows
    public T mapRow(ResultSet rs, int rowNum) {
        return objectMapper.readValue(rs.getString(1), classType);
    }

}
