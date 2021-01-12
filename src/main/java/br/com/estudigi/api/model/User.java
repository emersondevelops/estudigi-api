package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Role;
import br.com.estudigi.api.model.enums.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String fullName;

    @Column
    @Enumerated(EnumType.STRING)
    Sex sex;

    @Column
    @Temporal(TemporalType.DATE)
    Date birthDate;

    @Column
    String email;

    @Column(length = 20)
    String login;

    @Column
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column
    LocalDateTime registeredAt = LocalDateTime.now();

    @Column
    LocalDateTime lastUpdate;

    @Column
    LocalDateTime lastLogin;

    @Column
    Boolean active = true;

    @Column(length = 11)
    String phone;

    @Column
    String address;

    @Column
    String otherInfo;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ClassGroup> classGroups;
}
