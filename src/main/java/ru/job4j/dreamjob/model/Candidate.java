package ru.job4j.dreamjob.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class Candidate implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String description;
    private LocalDateTime dateOfBirth;
    private boolean visible;
    private byte[] photo;

    public Candidate(int id, String name, String surname, String description, LocalDateTime dateOfBirth, byte[] photo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.photo = photo;
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
