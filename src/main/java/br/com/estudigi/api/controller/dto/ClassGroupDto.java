package br.com.estudigi.api.controller.dto;

import br.com.estudigi.api.model.ClassGroup;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClassGroupDto {
    Integer classGroupId;
    String name;
    String grade;
    List<UserDto> members;
    String createdBy;
    String createdAt;
    String lastUpdate;

    public ClassGroupDto(ClassGroup classGroup) {
        this.classGroupId = classGroup.getId();
        this.name = classGroup.getName();
        this.grade = classGroup.getGrade().getDescription();
        this.members = new ArrayList<>();
        this.members.addAll(classGroup.getUsers().stream().map(UserDto::new).collect(Collectors.toList()));
        this.createdBy = classGroup.getCreatedBy().getFullName();
        this.createdAt = classGroup.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        if (classGroup.getLastUpdate() != null) {
            this.lastUpdate = classGroup.getLastUpdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
    }

    public static List<ClassGroupDto> convert(Page<ClassGroup> classGroups) {
        return classGroups.stream().map(ClassGroupDto::new).collect(Collectors.toList());
    }
}
