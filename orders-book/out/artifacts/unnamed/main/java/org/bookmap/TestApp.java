package org.bookmap;

import org.bookmap.config.AppConfig;
import org.bookmap.dao.FileReactiveReader;
import org.bookmap.dao.FileReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        FileReader fileDao = context.getBean(FileReactiveReader.class);
        fileDao.fromFile();
    }
}
