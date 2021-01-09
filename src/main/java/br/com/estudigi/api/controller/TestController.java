package br.com.estudigi.api.controller;

import br.com.estudigi.api.controller.dto.TestDto;
import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.model.Test;
import br.com.estudigi.api.repository.TestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    @CrossOrigin
    public List<TestDto> listAll(Pageable pageable) {
        Page<Test> tests = testRepository.findAll(pageable);
        return TestDto.convert(tests);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Test> create(@RequestBody Test test) {
        Test newTest = testRepository.save(test);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTest);
    }
}
