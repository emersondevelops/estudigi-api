package br.com.estudigi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "test_events")
@Getter
@Setter
public class TestEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    Test test;

    @OneToMany
    List<ClassGroup> classGroups;

    Double questionValue;
    Boolean repeatable;
    Integer repeatTimes;
    LocalDateTime createdAt;
    LocalDateTime lastUpdate;
}
