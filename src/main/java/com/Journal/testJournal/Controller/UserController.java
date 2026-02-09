package com.Journal.testJournal.Controller;

import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    ///  To get List
    @GetMapping()
    public ResponseEntity<?> list() {
        List<UserEntity> users = userService.list();
        return users.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found")
                : ResponseEntity.ok(users);
    }


    ///  to get by name
    @GetMapping("/{name}")
    public ResponseEntity<?> findByUser(@PathVariable String name) {
        Optional<UserEntity> user = userService.findByName(name);
        return user.isPresent()
                ? ResponseEntity.ok(user.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with name: " + name);
    }


    ///  to create a user
    @PostMapping("/create")
    public ResponseEntity<UserEntity> user(@RequestBody UserEntity crete) {

        if (crete.getUserName() == null || crete.getEmail() == null || crete.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity user = userService.create(crete);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable String id, @RequestBody UserEntity userEntity) {
        return userService.findById(id)
                .map((existing -> ResponseEntity.ok(userService.update(id, userEntity))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}/")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return userService.findById(id)
                .map(existing -> {
                    userService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
