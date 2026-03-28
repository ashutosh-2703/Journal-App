package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 */30 * * * ?")
    public void fetchUsersAndSendSaMails(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for(User user : users){
            emailService.sendEmail(user.getEmail(),"Sentiment Analysis Report","Hi! How are you? \n " +
                    "Your Sentiment level is very good. \n " +
                    "Best regards \n" +
                    "Ashutosh Sharma" );
        }
    }
}
