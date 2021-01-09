package br.com.estudigi.api.controller.dto;

import br.com.estudigi.api.model.Question;
import br.com.estudigi.api.model.Test;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestDto {

    Integer id;
    String name;
    String subject;
    String createdBy;
    String createdAt;
    List<Question> questions;

    public TestDto(Test test) {
        this.id = test.getId();
        this.name = test.getName();
        this.subject = test.getSubject().getDescription();
        this.createdBy = test.getCreatedBy().getFullName();
        this.createdAt = test.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.questions = test.getQuestions();
    }

    public static List<TestDto> convert(Page<Test> tests) {
        return tests.stream().map(TestDto::new).collect(Collectors.toList());
    }
}
