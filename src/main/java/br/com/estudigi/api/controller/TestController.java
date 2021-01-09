package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.model.Test;
import br.com.estudigi.api.repository.TestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(testRepository.findAll());
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Test> create(@RequestBody Test test) {
        Test newTest = testRepository.save(test);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTest);
    }
}
