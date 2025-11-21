package org.example.processor;

import org.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class LoginProcessor {
    private final Users users;
    private final LoggedUserProcessor loggedUserProcessor;

    @Autowired
    public LoginProcessor(
            Users users,
            LoggedUserProcessor loggedUserProcessor
    ) {
        this.users = users;
        this.loggedUserProcessor = loggedUserProcessor;
    }

    public boolean checkLogin(String username, String password) {
        if (users.checkPassword(username, password)) {
            loggedUserProcessor.setLogged(true);
            return true;
        }
        return false;
    }
}
