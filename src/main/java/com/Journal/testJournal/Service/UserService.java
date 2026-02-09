package com.Journal.testJournal.Service;

import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User not found with id: " + id);
    }
}

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    ///  to get all Lists
    public List<UserEntity> list() {
        return userRepo.findAll();
    }

    ///  to get user by name ...
  public Optional<UserEntity>findByName( String userName){
       return userRepo.findByUserName(userName);
    }
    ///  to create a user...

    public UserEntity create(UserEntity create){
           userRepo.save(create);
        return create;
    }

     /// to update
    public UserEntity update(String  id, UserEntity updateUser ){
        UserEntity user =userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
               user.setEmail(updateUser.getEmail());
               user.setMobileNo(updateUser.getMobileNo());
               user.setPassword(updateUser.getPassword());
              return  userRepo.save(user);
    }
    ///  to delete
    public Optional<UserEntity>delete(String id){
        Optional<UserEntity> user=userRepo.findById(id);
         user.ifPresent(u->userRepo.deleteById(id));
         return user;
    }

    public Optional<UserEntity> findById(String id) {
        return userRepo.findById(id);
    }

    public Optional<UserEntity> findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
}
