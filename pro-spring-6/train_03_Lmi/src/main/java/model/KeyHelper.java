package model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("keyHelper")
@Scope("prototype")
public class KeyHelper {
    public void open() {
    }
}
