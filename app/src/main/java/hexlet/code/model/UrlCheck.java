package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlCheck {
    private int id;
    private int urlId;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private String createdAtFormatted;
    private LocalDateTime createdAt;

    public UrlCheck(int urlId, int statusCode, String title, String h1, String description) {
        this.urlId = urlId;
        this.h1 = h1;
        this.title = title;
        this.description = description;
        this.statusCode = statusCode;
    }
}
