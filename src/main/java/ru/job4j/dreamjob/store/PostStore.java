package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PostStore {

    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        posts.put(1, new Post(1, "Junior Java Job", "Junior", formatter.format(now)));
        posts.put(2, new Post(2, "Middle Java Job", "Middle", formatter.format(now)));
        posts.put(3, new Post(3, "Senior Java Job", "Senior", formatter.format(now)));
    }

    public static PostStore getInstance() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

}
