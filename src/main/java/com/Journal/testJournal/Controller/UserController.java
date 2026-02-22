package com.Journal.testJournal.Controller;

import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Service.UserService;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    ///  To get List
//    @GetMapping()
//    public ResponseEntity<?> list() {
//        List<UserEntity> users = userService.list();
//        return users.isEmpty()
//                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found")
//                : ResponseEntity.ok(users);
//    }


    ///  to get by name
    @GetMapping
    public ResponseEntity<?> findByUser(Authentication authentication) {
        String userName= authentication.getName();
        UserEntity user = userService.findByUserName(userName)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return ResponseEntity.ok(user);

    }

    @PutMapping
    public ResponseEntity<?> update( @RequestBody UserEntity userEntity,Authentication authentication) {
       String userName= authentication.getName();
       userService.update(userName,userEntity);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Authentication authentication) {
       String userName= authentication.getName();

       userService.delete(userName);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
