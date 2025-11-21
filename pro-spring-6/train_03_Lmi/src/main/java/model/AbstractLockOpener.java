package model;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLockOpener")
public abstract class AbstractLockOpener implements LockOpener {

    @Lookup("keyHelper")
    @Override
    public abstract KeyHelper getKey();

    @Override
    public void openLock() {
        getKey().open();
    }
}
