package com.Journal.testJournal.Service;

import com.Journal.testJournal.Entity.JournalEntity;
import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Repository.JournalRepo;
import com.Journal.testJournal.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserRepo userRepo;

    /// create journal and link with user
    public JournalEntity create(JournalEntity journal, String userId) {

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        JournalEntity savedJournal = journalRepo.save(journal);

        user.getList().add(savedJournal);   // 🔥 linking
        userRepo.save(user);

        return savedJournal;
    }

    /// get all journals of a user
    public java.util.List<JournalEntity> journalList(String userName) {
        UserEntity user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userName));

        return user.getList();
    }

    /// get journal by id
    public JournalEntity journalEntity(String journalId) {
        return journalRepo.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + journalId));
    }

    /// update journal
    public JournalEntity update(String journalId, JournalEntity updatedJournal) {
        JournalEntity journal = journalRepo.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + journalId));

        journal.setTitle(updatedJournal.getTitle());
        journal.setContent(updatedJournal.getContent());
        journal.setDate(updatedJournal.getDate());

        return journalRepo.save(journal);
    }

    /// delete journal from user + collection
    public void delete(String userId, String journalId) {

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JournalEntity journal = journalRepo.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found"));

        user.getList().removeIf(j -> j.getId().equals(journalId));
        userRepo.save(user);

        journalRepo.deleteById(journalId);
    }
}