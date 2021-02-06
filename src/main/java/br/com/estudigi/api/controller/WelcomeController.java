package br.com.estudigi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    ResponseEntity<?> welcome () {
        return ResponseEntity.ok("Welcome to the EstuDigi API!");
    }
}
