package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.time.LocalDateTime;

@Controller
public class CandidateController {

    private CandidateStore store = CandidateStore.getInstance();

    @GetMapping(value = "/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("candidate", new Candidate(0, "test", "test", LocalDateTime.now()));
        return "addCandidate";
    }

}
