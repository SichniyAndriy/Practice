package org.example.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Users {
    private final Map<String, String> accounts = new HashMap<>();

    {
        accounts.put("Bob","123");
        accounts.put("Mike","456");
        accounts.put("Maria","789");
        accounts.put("Rob","qwe");
    }

    public boolean checkPassword(String username, String password) {
        if (accounts.containsKey(username)) {
            return accounts.get(username).equals(password);
        }
        return false;
    }
}
