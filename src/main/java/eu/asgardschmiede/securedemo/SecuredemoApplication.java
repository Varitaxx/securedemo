package eu.asgardschmiede.securedemo;

import eu.asgardschmiede.securedemo.model.User;
import eu.asgardschmiede.securedemo.model.UserRole;
import eu.asgardschmiede.securedemo.model.UserStatus;
import eu.asgardschmiede.securedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecuredemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   /* @Value("$(db.admin.user.reset")
    private boolean adminReset;
*/
    public static void main(String[] args) {
        SpringApplication.run(SecuredemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@shield.org");
        user.setPassword(passwordEncoder.encode("geheim123"));
        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        user = new User();
        user.setUsername("p.parker");
        user.setEmail("p.parker@shield.org");
        user.setPassword(passwordEncoder.encode("geheim123"));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        user = new User();
        user.setUsername("b.banner");
        user.setEmail("b.banner@shield.org");
        user.setPassword(passwordEncoder.encode("geheim123"));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.BLOCKED);
        userRepository.save(user);
    }

}
