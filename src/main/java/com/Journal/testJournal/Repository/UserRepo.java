package com.Journal.testJournal.Repository;

import com.Journal.testJournal.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntity,String> {


    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findById(String id);

    void deleteByUserName(String userName);
}
