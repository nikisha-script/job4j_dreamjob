package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> candidateMap = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidateMap.put(1, new Candidate(1, "Danil", "Nikishin", LocalDateTime.of(1996, 2, 16, 0, 0)));
        candidateMap.put(2, new Candidate(2, "Ivan", "Ivanov", LocalDateTime.of(1987, 2, 22, 0, 0)));
        candidateMap.put(3, new Candidate(3, "Max", "Lebovskiy", LocalDateTime.of(2022, 5, 27, 0, 0)));
    }

    public static CandidateStore getInstance() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidateMap.values();
    }

}
