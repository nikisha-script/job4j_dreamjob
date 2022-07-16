package ru.job4j.dreamjob.store;

import org.junit.Test;
import ru.job4j.App;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new App().loadPool());
        Post post = new Post(0, "test", "test", LocalDateTime.now(), true, 1);
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new App().loadPool());
        Candidate candidate = new Candidate(0, "test", "test", "test", LocalDateTime.now(), new byte[0], true);
        store.add(candidate);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(candidate.getName()));
    }

}