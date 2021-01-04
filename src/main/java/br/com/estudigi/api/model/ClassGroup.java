package br.com.estudigi.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "class_groups")
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @ManyToMany
    List<User> users;

    @Column
    LocalDateTime createdAt;

    @Column
    LocalDateTime lastUpdate;
}
