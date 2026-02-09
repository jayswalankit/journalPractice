package com.Journal.testJournal.Controller;

import com.Journal.testJournal.Entity.JournalEntity;
import com.Journal.testJournal.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    /// get all journals of a user
    @GetMapping("/{userName}/list")
    public ResponseEntity<List<JournalEntity>> getAll(@PathVariable String userName) {
        return ResponseEntity.ok(journalService.journalList(userName));
    }

    /// get journal by id
    @GetMapping("/id/{journalId}")
    public ResponseEntity<JournalEntity> getById(@PathVariable String journalId) {
        return ResponseEntity.ok(journalService.journalEntity(journalId));
    }

    /// create journal for user
    @PostMapping("/{userId}/create")
    public ResponseEntity<JournalEntity> create(
            @PathVariable String userId,
            @RequestBody JournalEntity journal) {

        JournalEntity created = journalService.create(journal, userId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /// update journal
    @PutMapping("/{journalId}")
    public ResponseEntity<JournalEntity> update(
            @PathVariable String journalId,
            @RequestBody JournalEntity journal) {

        return ResponseEntity.ok(journalService.update(journalId, journal));
    }

    /// delete journal
    @DeleteMapping("/{userId}/{journalId}")
    public ResponseEntity<?> delete(
            @PathVariable String userId,
            @PathVariable String journalId) {

        journalService.delete(userId, journalId);
        return ResponseEntity.noContent().build();
    }
}