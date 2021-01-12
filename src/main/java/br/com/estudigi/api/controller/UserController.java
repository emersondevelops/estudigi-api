package br.com.estudigi.api.controller;

import br.com.estudigi.api.model.ClassGroup;
import br.com.estudigi.api.model.User;
import br.com.estudigi.api.repository.TestRepository;
import br.com.estudigi.api.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserRepository userRepository;
    final TestRepository testRepository;

    public UserController(UserRepository userRepository, TestRepository testRepository) {
        this.userRepository = userRepository;
        this.testRepository = testRepository;
    }

    @GetMapping
    @CrossOrigin
    public Page<User> read(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @GetMapping("/role/{role}")
    @CrossOrigin
    public Page<User> readByRole(Pageable pageable, @PathVariable String role) {
        Page<User> users = userRepository.findByRole(pageable, role);
        return users;
    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<User> readById(@PathVariable Integer userId)
            throws NotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Class group not found for id: " + userId));
        return ResponseEntity.ok(existingUser);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<User> update(@PathVariable Integer userId, @RequestBody User user)
            throws NotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found for id: " + userId));
        existingUser.setFullName(user.getFullName());

        final User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

//    @GetMapping("/{userId}/test-events")
//    public List<TestDto> readAllTestEventsByUser(@PathVariable Integer userId) {
//        return TestDto.convert(testRepository.getTestEventsByUser(userId));
//    }

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
