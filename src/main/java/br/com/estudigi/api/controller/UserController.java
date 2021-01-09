package br.com.estudigi.api.controller;

import br.com.estudigi.api.controller.dto.UserDto;
import br.com.estudigi.api.model.User;
import br.com.estudigi.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @CrossOrigin
    public List<UserDto> read(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return UserDto.convert(users);
    }

    @GetMapping("/role/{role}")
    @CrossOrigin
    public List<UserDto> readByRole(Pageable pageable, @PathVariable String role) {
        Page<User> users = userRepository.findByRole(pageable, role);
        return UserDto.convert(users);
    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<UserDto> readById(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
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
