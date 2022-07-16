package ru.job4j.dreamjob.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class Post implements Serializable {

    private int id;
    private String name;
    private String description;
    private LocalDateTime created;
    private boolean visible;
    private int idCity;

    public Post(int id,
                String name,
                String description,
                LocalDateTime created,
                boolean visible,
                int idCity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.idCity = idCity;
    }

}
