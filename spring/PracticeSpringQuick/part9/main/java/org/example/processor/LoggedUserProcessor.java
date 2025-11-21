package org.example.processor;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Service
@SessionScope
public class LoggedUserProcessor {
    private boolean logged = false;
}
