package zaman.example.cricnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CricnewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CricnewsApplication.class, args);
    }

}
