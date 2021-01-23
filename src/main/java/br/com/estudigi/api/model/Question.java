package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Grade;
import br.com.estudigi.api.model.enums.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String topic;

    @Enumerated(EnumType.STRING)
    Subject subject;

    @Enumerated(EnumType.STRING)
    Grade grade;

    @Column(columnDefinition = "text")
    String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_choice",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id"))
    List<Choice> choices;

    @ManyToOne
    User createdBy;

    @Column
    LocalDateTime createdAt = LocalDateTime.now();

    @Column
    LocalDateTime lastUpdate;

    @Override
    public int compareTo(Question question) {
        if (getId() == null || question.getId() == null) {
            return 0;
        }
        return getId().compareTo(question.getId());
    }
}
