package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.model.Test;
import br.com.estudigi.api.model.TestResult;
import br.com.estudigi.api.repository.QuestionRepository;
import br.com.estudigi.api.repository.TestRepository;
import br.com.estudigi.api.repository.TestResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test-result")
@CrossOrigin
public class TestResultController {

    final TestResultRepository testResultRepository;
    final TestRepository testRepository;
    final QuestionRepository questionRepository;

    public TestResultController(TestResultRepository testResultRepository,
                                TestRepository testRepository,
                                QuestionRepository questionRepository) {
        this.testResultRepository = testResultRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping
    public ResponseEntity<TestResult> create(@RequestBody TestResult testResult) {
        Test test = testRepository.findById(testResult.getTest().getId()).orElse(null);

        assert test != null;
        List<Question> questions = test.getQuestions();
        Collections.sort(questions);
        Double score = 0.0;

        for (int i = 0; i < questions.size(); i++) {

            // int questionId = testResult.getAnswers().get(i).getQuestionId();
            // As respostas enviadas precisam vir ordenadas por questionId senão a comparação falhará.
            int choiceId = testResult.getAnswers().get(i).getChoiceId();

            for (int j = 0; j < questions.get(i).getChoices().size(); j++) {
                if (questions.get(i).getChoices().get(j).getCorrect() && questions.get(i).getChoices().get(j).getId() == choiceId) {
                    score += test.getQuestionValue();
                }
            }
        }

        testResult.setScore(score);
        int trials = testResultRepository.findTrials(testResult.getUser().getId(), testResult.getTest().getId());
        testResult.setTrial(++trials);

        return ResponseEntity.status(HttpStatus.CREATED).body(testResultRepository.save(testResult));
    }

    @GetMapping
    public ResponseEntity<?> readAll() { return ResponseEntity.ok(testResultRepository.findAll()); }

    @GetMapping("/{testResultId}")
    public ResponseEntity<?> readById(@PathVariable Integer testResultId) {
        TestResult testResult = testResultRepository.findById(testResultId).orElse(null);
        if (testResult != null) {
            return ResponseEntity.ok().body(testResult);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-test-id/{testId}")
    public Page<TestResult> readAllByTestId(@PathVariable Integer testId, Pageable pageable) {
        return testResultRepository.findAllByTestId(pageable, testId);
    }

    @PutMapping("/{testResultId}")
    public ResponseEntity<?> update(@PathVariable Integer testResultId, @RequestBody TestResult updatedTestResult) {
        TestResult testResult = testResultRepository.findById(testResultId).orElse(null);
        if (testResult != null) {
            testResult.setTeacherComment(updatedTestResult.getTeacherComment());
            testResultRepository.save(testResult);
            return ResponseEntity.ok(testResult);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test result id " + testResultId + " not found!");
    }

    @DeleteMapping("/{testResultId}")
    public ResponseEntity<?> delete(@PathVariable Integer testResultId) {
        TestResult testResult = testResultRepository.findById(testResultId).orElse(null);
        if (testResult != null) {
            testResultRepository.delete(testResult);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Test result id " + testResultId + " deleted!");
        }
        return ResponseEntity.notFound().build();
    }
}
