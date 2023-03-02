package eu.asgardschmiede.securedemo.service;

import eu.asgardschmiede.securedemo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional //Sorgt dafür, wenn die Transaktion abgelaufen ist, der Service neu aufgebaut wird
public class CleanUpService {

    @Autowired
    private TokenRepository tokenRepository;

   // @Scheduled(cron = "@daily")//@yearly, @daily, @midnight, @hourly
    @Scheduled(cron = "0 */60 * * * *")//sec min std tag monat wochentag
    public void deleteExpiredTokens(){
        tokenRepository.deleteByCreatedAtBefore(LocalDateTime.now().minusDays(7));
        System.out.println("Cleaned Up");
    }
}
