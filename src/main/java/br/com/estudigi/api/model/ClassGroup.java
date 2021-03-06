package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Grade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_groups")
@Getter
@Setter
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column
    @Enumerated(EnumType.STRING)
    Grade grade;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "class_group_user",
            joinColumns = @JoinColumn(name = "class_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> users;

    @ManyToOne
    User createdBy;

    @Column
    LocalDateTime createdAt = LocalDateTime.now();

    @Column
    LocalDateTime lastUpdate;
}
