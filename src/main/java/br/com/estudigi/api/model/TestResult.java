package br.com.estudigi.api.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "test_results")
@Getter
@Setter
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    Test test;

    @ManyToOne
    User user;

    Integer trial;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "test_result_answer",
            joinColumns = @JoinColumn(name = "test_result_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    List<Answer> answers;

    Double score;

    @Column(columnDefinition = "text")
    String teacherComment;

    LocalDateTime startedAt;

    LocalDateTime sentAt;
}
