package hexlet.code.repository;

import hexlet.code.model.Url;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class UrlRepository extends BaseRepository {

    public static void save(Url url) throws SQLException {
        String sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            var currentDate = LocalDateTime.now();
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            var formattedDate = currentDate.format(formatter);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(currentDate));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getInt(1));
                url.setCreatedAt(currentDate);
                url.setCreatedAtFormatted(formattedDate);
            } else {
                throw new SQLException("ID ERROR");
            }

        }
    }
    public static Optional<Url> find(int id) throws SQLException {
        var sql = "SELECT * FROM urls WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                var formattedDate = createdAt.format(formatter);
                var url = new Url(name);
                url.setId(id);
                url.setCreatedAtFormatted(formattedDate);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static boolean findMatchesByName(String name) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var so = resultSet.getString("name");
                if (so.equals(name)) {
                    return false;
                }
            }
        }
        return true;

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
