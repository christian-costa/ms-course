package br.com.saves.hroauth.services;

import br.com.saves.hroauth.entities.User;
import br.com.saves.hroauth.feingclients.UserFeingClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

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
}
