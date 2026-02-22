package com.Journal.testJournal.Controller;

import com.Journal.testJournal.Entity.JournalEntity;
import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Service.JournalService;
import com.Journal.testJournal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    /// get all journals of a user
    @GetMapping
    public ResponseEntity<?> getAll(Authentication authentication) {
       String userName=authentication.getName();
        UserEntity user=userService.findByUserName(userName).orElseThrow(()->new RuntimeException("Not found User "+userName));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    /// get journal by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        Optional<JournalEntity>journal=journalService.findById(id);
        return journal .map(entry->new ResponseEntity<>(entry,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /// create journal for user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody JournalEntity journal,Authentication authentication) {
        String userName=authentication.getName();

        JournalEntity saved=journalService.create(journal,userName);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    /// update journal
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody JournalEntity journal) {

       Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       String userName= authentication.getName();
       JournalEntity updated=journalService.update(id,journal,userName);

        if (updated == null)
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /// delete journal
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        journalService.deleteById(id, userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}