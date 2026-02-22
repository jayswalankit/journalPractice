package com.Journal.testJournal.Service;

import com.Journal.testJournal.Entity.UserEntity;
import com.Journal.testJournal.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;


    ///  to get all Lists
    public List<UserEntity> list() {
        return userRepo.findAll();
    }

    ///  to get user by name ...
  public Optional<UserEntity>findByName( String userName){
       return userRepo.findByUserName(userName);
    }
    ///  to create a user...

    @Transactional
    public UserEntity create(UserEntity create){
          create.setPassword(passwordEncoder.encode(create.getPassword()));
          create.setRoles(List.of("USER"));
          return userRepo.save(create);
    }
     /// to update
     @Transactional
    public UserEntity update(String  userName, UserEntity updateUser ){
         UserEntity existingUser = userRepo.findByUserName(userName)
                 .orElseThrow(() ->
                         new RuntimeException("User Not Found " + userName)
                 );
         if(updateUser.getUserName()!=null){
           existingUser.setUserName(updateUser.getUserName());
         }

         if(updateUser.getEmail()!=null){
             existingUser.setEmail(updateUser.getEmail());
         }

         if(updateUser.getMobileNo()!=null){
             existingUser.setMobileNo(updateUser.getMobileNo());
         }

         if(updateUser.getPassword()!=null){
             existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
         }
             return userRepo.save(existingUser);
    }
    ///  to delete
    @Transactional
    public void delete(String userName) {
      UserEntity user=userRepo.findByUserName(userName)
              .orElseThrow(()->new RuntimeException("User not found "+ userName));
      userRepo.delete(user);
    }

    public Optional<UserEntity> findById(String id) {
        return userRepo.findById(id);
    }

    public Optional<UserEntity> findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }


    ///  to save journal entries
    public void save(UserEntity user) {
        userRepo.save(user);
    }
}
