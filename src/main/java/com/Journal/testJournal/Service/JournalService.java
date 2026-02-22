package com.Journal.testJournal.Service;

import com.Journal.testJournal.Entity.JournalEntity;
import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    /// create journal and link with user
    @Transactional
    public JournalEntity create(JournalEntity journal, String userName) {

        UserEntity user = userService.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userName));

        JournalEntity savedJournal = journalRepo.save(journal);

        user.getList().add(savedJournal);
        userService.save(user);

        return savedJournal;
    }




    /// get journal by id
    public JournalEntity journalEntity(String journalId) {
        return journalRepo.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + journalId));
    }

    /// update journal
    public JournalEntity update(String id, JournalEntity updatedJournal , String userName) {
        UserEntity user= userService.findByUserName(userName)
                .orElseThrow(()-> new RuntimeException("User Not Found" + userName));
        updatedJournal.setDate(LocalDateTime.now());
       JournalEntity saved=journalRepo.save(updatedJournal);
       user.getList().add(saved);
       userService.save(user);
                return saved;
    }

    /// delete journal from user + collection
    @Transactional
    public void deleteById(String userId, String userName) {

        UserEntity user = userService.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));


        user.getList().removeIf(j -> j.getId().equals(userId));
        userService.save(user);

        journalRepo.deleteById(userId);
    }

    public Optional<JournalEntity> findById(String id) {
       return  journalRepo.findById(id);
    }


}