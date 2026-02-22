package com.Journal.testJournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestJournalApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context =SpringApplication.run(TestJournalApplication.class, args);

        String mongoUri = context.getEnvironment().getProperty("spring.data.mongodb.uri");
        System.out.println("--------------------------------------------------");
        System.out.println("🔥 MY MONGO URI IS: " + mongoUri);
        System.out.println("--------------------------------------------------");
	}


}
