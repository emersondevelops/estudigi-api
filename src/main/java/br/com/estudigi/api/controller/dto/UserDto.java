package br.com.estudigi.api.controller.dto;

import br.com.estudigi.api.model.User;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDto {

    Integer userId;
    String fullName;
    String email;
    String role;
    //List<ClassGroupDto> classGroups;

    public UserDto(User user) {
        this.userId = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.role = user.getRole().getDescription();
        //this.classGroups = new ArrayList<>();
        //this.classGroups.addAll(user.getClassGroups().stream().map(ClassGroupDto::new).collect(Collectors.toList()));
    }

    public static List<UserDto> convert(Page<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
