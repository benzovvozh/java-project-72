package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static hexlet.code.repository.BaseRepository.dataSource;

public class UrlCheckRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks (url_id, statusCode, title, h1, description, created_at)"
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
                var statusCode = resultSet.getInt("statusCode");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var desc = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var urlCheck = new UrlCheck(urlId, statusCode, title, h1, desc);
                var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                var formattedDate = createdAt.format(formatter);
                urlCheck.setId(urlCheckId);
                urlCheck.setCreatedAt(createdAt);
                urlCheck.setCreatedAtFormatted(formattedDate);
                result.add(urlCheck);
            }

            return result;
        }
    }

    public static UrlCheck getLastCheck(int id) {
        try {
            var list = getEntities(id);
            return list.getLast();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка getLastCheck");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static String getLastCheckTime(int id) {
        try {
            var list = getEntities(id);
            return list.getLast().getCreatedAtFormatted();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static String getLastCheckStatusCode(int id) {
        try {
            var list = getEntities(id);
            return String.valueOf(list.getLast().getStatusCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
