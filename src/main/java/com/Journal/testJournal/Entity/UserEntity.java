package com.Journal.testJournal.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private  String userName;
    @NonNull
    private String email;
    private String mobileNo;
    @NonNull
    private String password;

    @DBRef(lazy = true)
    private List<JournalEntity>list=new ArrayList<>();
    private List<String>roles;



}
