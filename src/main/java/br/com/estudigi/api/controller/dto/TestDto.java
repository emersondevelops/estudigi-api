//package br.com.estudigi.api.controller.dto;
//
//import br.com.estudigi.api.model.Test;
//import lombok.Getter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.time.format.DateTimeFormatter;
//import java.util.stream.Collectors;
//
//@Getter
//public class TestDto {
//
//    Integer id;
//    String name;
//    List<String> classGroups;
//    String classGroup;
//    Double questionValue;
//    Integer repeatTimes;
//    String createdAt;
//    String lastUpdate;
//
//    public TestDto(Test test) {
//        this.id = test.getId();
//        this.name = test.getName();
//        this.classGroups = new ArrayList<>();
//        for (int i = 0; i < test.getClassGroups().size(); i++) {
//            this.classGroups.add(test.getClassGroups().get(i).getName());
//        }
//        this.questionValue = test.getQuestionValue();
//        this.repeatTimes = test.getRepeatTimes();
//        this.createdAt = test.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE);
//        try {
//            this.lastUpdate = test.getLastUpdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
//        } finally {
//            this.lastUpdate = null;
//        }
//
//    }
//
//    public static java.util.List<TestDto> convert(List<Test> testList) {
//        return testList.stream().map(TestDto::new).collect(Collectors.toList());
//    }
//}
