package ru.job4j.dreamjob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class Post {

    private int id;
    private String name;
    private String description;
    private LocalDateTime created;


}
