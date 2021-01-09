package br.com.estudigi.api.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_results")
@Getter
@Setter
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    TestEvent testEvent;

    @Column(columnDefinition = "json")
    @JsonRawValue
    String answers;

    Double score;

    @Column(columnDefinition = "text")
    String teacherComment;

    LocalDateTime startedAt;
    LocalDateTime sentAt;
}
