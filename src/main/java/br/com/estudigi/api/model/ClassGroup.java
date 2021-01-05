package br.com.estudigi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "class_groups")
@Getter
@Setter
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @ManyToMany(mappedBy="classGroups", cascade = CascadeType.ALL)
    List<User> users;

    @Column
    LocalDateTime createdAt;

    @Column
    LocalDateTime lastUpdate;
}
