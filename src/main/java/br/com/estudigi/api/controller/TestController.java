package br.com.estudigi.api.controller;

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

    @PostMapping
    public ResponseEntity<Test> create(@RequestBody Test test) {
        Test newTest = testRepository.save(test);
        return ResponseEntity.ok().body(newTest);
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(testRepository.findAll());
    }

    @GetMapping("/{testId}")
    @CrossOrigin
    public ResponseEntity<?> readById(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {
            return ResponseEntity.ok(test);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }

    @PutMapping("/{testId}")
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {
            return null;
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }

    @DeleteMapping("/{testId}")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {
            testRepository.delete(test);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Test id " + testId + " deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }
}
