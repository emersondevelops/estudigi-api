package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.Choice;
import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.repository.ChoiceRepository;
import br.com.estudigi.api.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    final QuestionRepository questionRepository;
    final ChoiceRepository choiceRepository;

    public QuestionController(QuestionRepository questionRepository, ChoiceRepository choiceRepository) {

        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Question> create(@RequestBody Question question) {
        Question newQuestion = questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
    }

    @GetMapping
    @CrossOrigin
    public Page<Question> listAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    /*
    *     @GetMapping
    public Page<UserDto> readAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserDto::new);
    }
    * */

    @GetMapping("/{questionId}")
    @CrossOrigin
    public ResponseEntity<?> readById(@PathVariable Integer questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question id " + questionId + " not found!");
    }

    @PutMapping("/{questionId}")
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable Integer questionId, @RequestBody Question updatedQuestion) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {

            List<Choice> choices = question.getChoices();
            for (int i = 0; i < choices.size(); i++) {
                question.getChoices().get(i).setText(updatedQuestion.getChoices().get(i).getText());
                question.getChoices().get(i).setCorrect(updatedQuestion.getChoices().get(i).getCorrect());
            }
            question.setTopic(updatedQuestion.getTopic());
            question.setSubject(updatedQuestion.getSubject());
            question.setGrade(updatedQuestion.getGrade());
            question.setTitle(updatedQuestion.getTitle());
            questionRepository.save(question);
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question id " + questionId + " not found!");
    }

    @DeleteMapping("/{questionId}")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable Integer questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            questionRepository.delete(question);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Question id " + questionId + " deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question id " + questionId + " not found!");
    }
}
