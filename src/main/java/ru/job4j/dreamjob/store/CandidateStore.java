package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public final class CandidateStore {

    private final Map<Integer, Candidate> candidateMap = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    private CandidateStore() {
        candidateMap.put(1, new Candidate(1, "Danil", "Nikishin", LocalDateTime.of(1996, 2, 16, 0, 0)));
        candidateMap.put(2, new Candidate(2, "Ivan", "Ivanov", LocalDateTime.of(1987, 2, 22, 0, 0)));
        candidateMap.put(3, new Candidate(3, "Max", "Lebovskiy", LocalDateTime.of(2022, 5, 27, 0, 0)));
    }


    public Collection<Candidate> findAll() {
        return candidateMap.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ids.incrementAndGet());
        candidateMap.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidateMap.values().stream().filter(post -> post.getId() == id).findFirst().get();
    }

    public void update(Candidate candidate) {
        candidateMap.put(candidate.getId(), candidate);
    }

}
