package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Role;
import br.com.estudigi.api.model.enums.Sex;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String fullName;

    @Column
    @Enumerated
    Sex sex;

    @Column
    @Temporal(TemporalType.DATE)
    Date birthDate;

    @Column
    String email;

    @Column(length = 10)
    String login;

    @Column
    String password;

    @Column
    @Enumerated
    Role role;

    @Column
    LocalDateTime registeredAt;

    @Column
    LocalDateTime lastUpdate;

    @Column
    LocalDateTime lastLogin;

    @Column
    @ManyToMany
    List<ClassGroup> classGroups;

    @Column
    Boolean active;

    @Column(length = 10)
    String phone;

    @Column
    String address;

    @Column
    String otherInfo;
}
