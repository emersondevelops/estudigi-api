package br.com.estudigi.api.controller;

import br.com.estudigi.api.controller.dto.ClassGroupDto;
import br.com.estudigi.api.model.ClassGroup;
import br.com.estudigi.api.model.User;
import br.com.estudigi.api.repository.ClassGroupRepository;
import br.com.estudigi.api.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/class-group")
public class ClassGroupController {

    final ClassGroupRepository classGroupRepository;
    final UserRepository userRepository;

    public ClassGroupController(ClassGroupRepository classGroupRepository, UserRepository userRepository) {
        this.classGroupRepository = classGroupRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<ClassGroup> create(@RequestBody ClassGroup classGroup) {
        ClassGroup newClassGroup = classGroupRepository.save(classGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClassGroup);
    }

    @GetMapping
    @CrossOrigin
    public List<ClassGroupDto> read(Pageable pageable) {
        Page<ClassGroup> classGroups = classGroupRepository.findAll(pageable);
        return ClassGroupDto.convert(classGroups);
    }

    @GetMapping("/{classGroupId}")
    @CrossOrigin
    public ResponseEntity<?> readById(@PathVariable Integer classGroupId) {
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        if (classGroup != null) {
            ClassGroupDto classGroupDto = new ClassGroupDto(classGroup);
            return ResponseEntity.ok(classGroupDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class group id: " + classGroupId + " not found!");
    }

    @PutMapping("{classGroupId}/add-user/{userId}")
    @CrossOrigin
    public ResponseEntity<?> addUser(@PathVariable Integer classGroupId, @PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        if (user != null && classGroup != null) {
            classGroup.getUsers().add(user);
            classGroupRepository.save(classGroup);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(classGroup);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class group or user not found!");
    }

    @PutMapping("{classGroupId}/remove-user/{userId}")
    @CrossOrigin
    public ResponseEntity<?> removeUser(@PathVariable Integer classGroupId, @PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        if (user != null && classGroup != null) {
            classGroup.getUsers().remove(user);
            classGroupRepository.save(classGroup);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(classGroup);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class group or user not found!");
    }

    @PutMapping("/{classGroupId}")
    @CrossOrigin
    public ResponseEntity<ClassGroup> update(@PathVariable Integer classGroupId, @RequestBody ClassGroup classGroup)
            throws NotFoundException {
        ClassGroup existingClassGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new NotFoundException("Class group not found for id: " + classGroupId));
        existingClassGroup.setName(classGroup.getName());
        existingClassGroup.setGrade(classGroup.getGrade());
        existingClassGroup.setLastUpdate(LocalDateTime.now());

        final ClassGroup updatedClassGroup = classGroupRepository.save(existingClassGroup);
        return ResponseEntity.ok(updatedClassGroup);
    }

    // Bom exemplo de como tratar uma resposta
    @DeleteMapping("/{classGroupId}")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable Integer classGroupId) {
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        List<User> users = userRepository.findAll();
        if (classGroup != null) {
            // Remove todas as associações com usuários antes, pois sem isso os usuários também são removidos.
            classGroup.getUsers().removeAll(users);
            classGroupRepository.save(classGroup);
            classGroupRepository.deleteById(classGroupId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Class group id: " + classGroupId + " deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class group id: " + classGroupId + " not found!");
    }
}
