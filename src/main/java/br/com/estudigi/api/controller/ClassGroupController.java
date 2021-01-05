package br.com.estudigi.api.controller;

import br.com.estudigi.api.controller.dto.ClassGroupDto;
import br.com.estudigi.api.model.ClassGroup;
import br.com.estudigi.api.repository.ClassGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classGroup")
public class ClassGroupController {

    final
    ClassGroupRepository classGroupRepository;

    public ClassGroupController(ClassGroupRepository classGroupRepository) {
        this.classGroupRepository = classGroupRepository;
    }

    @GetMapping
    @CrossOrigin
    public List<ClassGroupDto> read(Pageable pageable) {
        Page<ClassGroup> classGroups = classGroupRepository.findAll(pageable);
        return ClassGroupDto.convert(classGroups);
    }
}
