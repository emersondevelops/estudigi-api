package br.com.estudigi.api.controller;

import br.com.estudigi.api.controller.dto.UserDto;
import br.com.estudigi.api.model.User;
import br.com.estudigi.api.repository.TestRepository;
import br.com.estudigi.api.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    final UserRepository userRepository;
    final TestRepository testRepository;

    public UserController(UserRepository userRepository, TestRepository testRepository) {
        this.userRepository = userRepository;
        this.testRepository = testRepository;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public Page<UserDto> readAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserDto::new);
    }

    @GetMapping("/role/{role}")
    public Page<UserDto> readByRole(Pageable pageable, @PathVariable String role) {
        Page<User> users = userRepository.findByRole(pageable, role);
        return users.map(UserDto::new);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> readById(@PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(new UserDto(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id: " + userId + " not found!");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Integer userId, @RequestBody User user)
            throws NotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found for id: " + userId));
        existingUser.setFullName(user.getFullName());

        final User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }
}
