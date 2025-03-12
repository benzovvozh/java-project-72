package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static hexlet.code.repository.BaseRepository.dataSource;

public class UrlCheckRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks (url_id, status_code, title, h1, description, created_at)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // устанавливаем id url
            preparedStatement.setInt(1, urlCheck.getUrlId());
            // устанавливаем дату создания проверки
            var currentDate = LocalDateTime.now();
            preparedStatement.setTimestamp(6, Timestamp.valueOf(currentDate));
            // устанавливаем status code
            preparedStatement.setInt(2, urlCheck.getStatusCode());
            // устанавливаем title
            preparedStatement.setString(3, urlCheck.getTitle());
            // устанавливаем h1 и description
            preparedStatement.setString(4, urlCheck.getH1());
            preparedStatement.setString(5, urlCheck.getDescription());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getInt(1));
                urlCheck.setCreatedAt(currentDate);
            } else {
                throw new SQLException("ID ERROR");
            }

        }
    }

    public static List<UrlCheck> getEntities(int id) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id  = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<UrlCheck>();
            while (resultSet.next()) {
                var urlId = resultSet.getInt("url_id");
                var urlCheckId = resultSet.getInt("id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var desc = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var urlCheck = new UrlCheck(urlId, statusCode, title, h1, desc);
                urlCheck.setId(urlCheckId);
                urlCheck.setCreatedAt(createdAt);
                result.add(urlCheck);
            }
            return result;
        }
    }

    public static HashMap<Integer, UrlCheck> getLastCheckInfo() {
        String sql = "SELECT DISTINCT ON (url_id) *"
                + " FROM url_checks"
                + " ORDER BY url_id, created_at DESC";
        var map = new HashMap<Integer, UrlCheck>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var urlId = resultSet.getInt("url_id");
                LocalDateTime time = resultSet.getTimestamp("created_at").toLocalDateTime();
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var h1 = resultSet.getString("h1");
                var urlCheck = new UrlCheck(urlId, statusCode, title, h1, description, time);
                map.put(urlId, urlCheck);
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("asd");
        }
    }
}

