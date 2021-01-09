package br.com.estudigi.api.controller.dto;

import br.com.estudigi.api.model.User;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentDto {

    Integer userId;
    String fullName;

    public StudentDto(User user) {
        this.userId = user.getId();
        this.fullName = user.getFullName();
    }

    public static List<StudentDto> convert(Page<User> students) {
        return students.stream().map(StudentDto::new).collect(Collectors.toList());
    }
}
