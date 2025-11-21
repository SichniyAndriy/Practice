package org.bookmap.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class FileWriterImpl implements FileWriter {
    private final File outputFile;

    public FileWriterImpl(@Autowired Environment environment) {
        String outputName = environment.getProperty("output.filename");
        assert outputName != null;
        outputFile = new File(outputName);
    }

    public void toFile(String line) {
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can not create \"output.txt\"", e);
            }
        }
        try {
            toFile(outputFile.toPath(), line + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to \"output.txt\"", e);
        }
    }



    private void toFile(Path path, String line) throws IOException {
        Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
    }
}
