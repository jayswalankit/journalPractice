package com.Journal.testJournal.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "JournalLists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntity {

    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;



}
