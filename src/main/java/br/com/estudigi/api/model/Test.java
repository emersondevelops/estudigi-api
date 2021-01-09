package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Subject;
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
    Subject subject;

    @OneToMany
    List<Question> questions;

    @ManyToOne
    User createdBy;

    LocalDateTime createdAt;
    LocalDateTime lastUpdate;
}
