package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class Url {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    public Url(String name) {
        this.name = name;
    }
}
