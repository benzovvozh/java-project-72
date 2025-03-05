package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Url {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private String createdAtFormatted;
    public Url(String name) {
        this.name = name;
    }
}
