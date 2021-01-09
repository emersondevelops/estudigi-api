package br.com.estudigi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(columnDefinition = "text")
    String title;

    @OneToMany(cascade = CascadeType.ALL)
    List<Choice> choices;

    public Question() {
    }
}
