//package br.com.estudigi.api.controller.dto;
//
//import br.com.estudigi.api.model.ClassGroup;
//import lombok.Getter;
//import org.springframework.data.domain.Page;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//public class ClassGroupDto {
//    Integer classGroupId;
//    String name;
//    List<UserDto> members;
//
//    public ClassGroupDto(ClassGroup classGroup) {
//        this.classGroupId = classGroup.getId();
//        this.name = classGroup.getName();
//        this.members = new ArrayList<>();
//        this.members.addAll(classGroup.getUsers().stream().map(UserDto::new).collect(Collectors.toList()));
//    }
//
//    public static List<ClassGroupDto> convert(Page<ClassGroup> classGroups) {
//        return classGroups.stream().map(ClassGroupDto::new).collect(Collectors.toList());
//    }
//}
