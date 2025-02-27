package hexlet.code.repository;

import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class UrlRepository extends BaseRepository {
    public static void save(Url url) throws SQLException {
        String sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            var currentDate = LocalDateTime.now();
            preparedStatement.setTimestamp(2, Timestamp.valueOf(currentDate));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getInt(1));
                url.setCreatedAt(currentDate);
            } else {
                throw new SQLException("ID ERROR");
            }

        }
    }

    public static List<Url> getEntities() throws SQLException {
        var sql = "SELECT * FROM urls";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Url>();
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var id = resultSet.getInt("id");
                var url = new Url(name);
                url.setId(id);
                url.setCreatedAt(createdAt);
                result.add(url);
            }
            return result;
        }
    }
}
