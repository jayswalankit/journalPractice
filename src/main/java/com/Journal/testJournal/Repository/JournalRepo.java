package com.Journal.testJournal.Repository;

import com.Journal.testJournal.Entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface JournalRepo extends MongoRepository<JournalEntity,String> {



}
