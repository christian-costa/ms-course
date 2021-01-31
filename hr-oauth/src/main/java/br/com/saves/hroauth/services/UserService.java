package br.com.saves.hroauth.services;

import br.com.saves.hroauth.entities.User;
import br.com.saves.hroauth.feingclients.UserFeingClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private UserFeingClient userFeingClient;

    public User findByEmail(String email){
        User user = userFeingClient.findByEmail(email).getBody();
        if(user == null) {
            log.error("Email not found: "+email);
            throw new IllegalArgumentException("Email not found");
        }
        log.info("Email found: "+email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userFeingClient.findByEmail(email).getBody();
        if(user == null) {
            log.error("Email not found: "+email);
            throw new UsernameNotFoundException("Email not found");
        }
        log.info("Email found: "+email);
        return user;
    }
}
