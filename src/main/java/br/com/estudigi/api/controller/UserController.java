package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.User;
import br.com.estudigi.api.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @CrossOrigin
    public Page<User> read(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<User> readById(@PathVariable Long userId)
            throws NotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Student not found for id: " + userId));
        return ResponseEntity.ok(existingUser);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

//    @PutMapping("/{studentId}")
//    @CrossOrigin
//    public ResponseEntity<Student> update(@PathVariable Long studentId, @RequestBody Student student)
//            throws NotFoundException {
//        Student existingStudent = studentRepository.findById(studentId)
//                .orElseThrow(() -> new NotFoundException("Student not found for id: " + studentId));
//        existingStudent.setName(student.getName());
//        existingStudent.setSex(student.getSex());
//        existingStudent.setBirthDate(student.getBirthDate());
//        existingStudent.setEmail(student.getEmail());
//        existingStudent.setClassName(student.getClassName());
//        existingStudent.setClassYear(student.getClassYear());

//        final Student updatedStudent = studentRepository.save(existingStudent);
//        return ResponseEntity.ok(updatedStudent);
//    }
//
//    @DeleteMapping("/{studentId}")
//    @CrossOrigin
//    public ResponseEntity<?> delete(@PathVariable Long studentId) {
//        Optional<Student> optional = studentRepository.findById(studentId);
//        if (optional.isPresent()) {
//            studentRepository.deleteById(studentId);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
