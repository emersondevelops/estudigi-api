package br.com.estudigi.api.controller;

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

import javax.transaction.Transactional;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class-group")
public class ClassGroupController {

    final ClassGroupRepository classGroupRepository;
    final UserRepository userRepository;

    public ClassGroupController(ClassGroupRepository classGroupRepository, UserRepository userRepository) {
        this.classGroupRepository = classGroupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @CrossOrigin
    public Page<ClassGroup> read(Pageable pageable) {
        Page<ClassGroup> classGroups = classGroupRepository.findAll(pageable);
        return classGroups;
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<ClassGroup> create(@RequestBody ClassGroup classGroup) {
        ClassGroup newClassGroup = classGroupRepository.save(classGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClassGroup);
    }

    @PutMapping("{classGroupId}/add-user/{userId}")
    @CrossOrigin
    public ResponseEntity<ClassGroup> addUser(@PathVariable Integer classGroupId, @PathVariable Integer userId)
            throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found for id: " + userId));
        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new NotFoundException("Class group not found for id: " + classGroupId));
        user.getClassGroups().add(classGroup);
        userRepository.save(user);
        return ResponseEntity.accepted().body(classGroup);
    }

    @PutMapping("{classGroupId}/remove-user/{userId}")
    @CrossOrigin
    public ResponseEntity<?> removeUser(@PathVariable Integer classGroupId, @PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        if (user != null && classGroup != null) {
            user.getClassGroups().remove(classGroup);
            userRepository.save(user);
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

    @GetMapping("/{classGroupId}")
    @CrossOrigin
    public ResponseEntity<ClassGroup> readById(@PathVariable Integer classGroupId)
            throws NotFoundException {
        ClassGroup existingClassGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new NotFoundException("Class group not found for id: " + classGroupId));
        return ResponseEntity.ok(existingClassGroup);
    }

    // Bom exemplo de como tratar uma resposta
    @DeleteMapping("/{classGroupId}")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable Integer classGroupId) {
        ClassGroup classGroup = classGroupRepository.findById(classGroupId).orElse(null);
        List<User> users = userRepository.findAll();
        if (classGroup != null) {
            classGroup.getUsers().removeAll(users);
            classGroupRepository.save(classGroup);
            classGroupRepository.deleteById(classGroupId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Class group id: " + classGroupId + " deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class group id: " + classGroupId + " not found!");
    }
}
