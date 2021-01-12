package br.com.estudigi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @OneToMany
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    List<Question> questions;

    @OneToMany
    @JoinTable(name = "test_class_group",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "class_group_id"))
    List<ClassGroup> classGroups;

    @ManyToOne
    User createdBy;

    Double questionValue;

    @Column(columnDefinition = "tinyint default '1'")
    Integer repeatTimes;

    LocalDateTime createdAt = LocalDateTime.now();

    LocalDateTime lastUpdate;
}
