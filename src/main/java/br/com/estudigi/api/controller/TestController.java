package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Test;
import br.com.estudigi.api.repository.TestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @PostMapping
    ResponseEntity<Test> create(@RequestBody Test test) {
        Test newTest = testRepository.save(test);
        return ResponseEntity.ok().body(newTest);
    }
}
