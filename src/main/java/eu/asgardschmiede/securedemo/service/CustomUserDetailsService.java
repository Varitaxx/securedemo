package eu.asgardschmiede.securedemo.service;

import eu.asgardschmiede.securedemo.model.User;
import eu.asgardschmiede.securedemo.model.UserStatus;
import eu.asgardschmiede.securedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> opt = userRepository.findByUsernameIgnoreCase(username);

        if(opt.isPresent()) {
            User u = opt.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(u.getUsername())
                    .password(u.getPassword())
                    .roles(u.getRole().toString())
                    .disabled(!u.getStatus().equals(UserStatus.ACTIVE))
                    .build();
        };

        throw new RuntimeException("User nicht gefunden");
    }
}
