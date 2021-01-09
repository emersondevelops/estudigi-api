package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    final
    QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(questionRepository.findAll());
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Question> create(@RequestBody Question question) {
        Question newQuestion = questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
