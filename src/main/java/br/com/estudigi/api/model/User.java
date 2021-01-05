package br.com.estudigi.api.model;

import br.com.estudigi.api.model.enums.Role;
import br.com.estudigi.api.model.enums.Sex;
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
    Long id;

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

    @Column
    @Enumerated(EnumType.STRING)
    Role role;

    @Column
    LocalDateTime registeredAt;

    @Column
    LocalDateTime lastUpdate;

    @Column
    LocalDateTime lastLogin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_class_group",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="class_group_id")})
    List<ClassGroup> classGroups;

    @Column
    Boolean active;

    @Column(length = 11)
    String phone;

    @Column
    String address;

    @Column
    String otherInfo;
}
