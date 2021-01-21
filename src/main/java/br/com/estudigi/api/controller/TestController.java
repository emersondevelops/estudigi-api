package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Test;
import br.com.estudigi.api.repository.TestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public Page<Test> readAll(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    @GetMapping("/created-by/{userId}")
    public Page<Test> readByCreatedBy(Pageable pageable, @PathVariable Integer userId) {
        return  testRepository.getTestsByCreatedBy(pageable, userId);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<?> readById(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {
            return ResponseEntity.ok(test);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }

    @PutMapping("/{testId}")
    public ResponseEntity<?> update(@PathVariable Integer testId, @RequestBody Test updatedTest) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {

            test.setName(updatedTest.getName());

            // O método clear() neste caso está sendo usado para remover as associações sem deletar as entidades.
            test.getQuestions().clear();
            test.setQuestions(updatedTest.getQuestions());

            test.getClassGroups().clear();
            test.setClassGroups(updatedTest.getClassGroups());

            test.setQuestionValue(updatedTest.getQuestionValue());
            test.setRepeatTimes(updatedTest.getRepeatTimes());
            test.setLastUpdate(LocalDateTime.now());
            testRepository.save(test);

            return ResponseEntity.ok(test);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<?> delete(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test != null) {
            testRepository.delete(test);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Test id " + testId + " deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test id " + testId + " not found!");
    }
}
