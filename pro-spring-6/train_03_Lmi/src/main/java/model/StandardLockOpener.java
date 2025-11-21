package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLockOpener")
public class StandardLockOpener implements LockOpener  {
    private final KeyHelper key;

    @Autowired
    StandardLockOpener(@Qualifier("keyHelper") KeyHelper keyHelper) {
        key = keyHelper;
    }

    @Override
    public KeyHelper getKey() {
        return key;
    }

    @Override
    public void openLock() {
        getKey().open();
    }
}
