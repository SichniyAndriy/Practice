package org.bookmap.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.BaseStream;
import org.bookmap.service.OperationService;
import org.bookmap.util.OperationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FileReactiveReader implements FileReader {
    private final File inputFile;

    private final OperationUtil operationUtil;

    public FileReactiveReader(@Autowired Environment environment,
                              @Autowired OperationUtil operationUtil)
    {
        this.operationUtil = operationUtil;
        String inputName = environment.getProperty("input.filename");
        assert inputName != null;
        inputFile = new File(inputName);
    }

    public void fromFile() {
        if (inputFile.exists()) {
            try {
                fromFile(inputFile.toPath(), operationUtil);
            } catch (IOException e) {
                throw new RuntimeException("Error reading \"input.txt\"", e);
            }
        } else {
            throw new RuntimeException("There is no \"input.txt\" file for reading.\n" +
                    "Create new or copy in root directory existed", new IOException());
        }
    }

    private void fromFile(Path path, OperationUtil operationUtil) throws IOException {
        Flux<String> flux = Flux.using(
                () -> Files.lines(path),
                Flux::fromStream,
                BaseStream::close);
        flux.subscribe(line -> {
            Optional<OperationService> operationService =
                    operationUtil.chooseOperation(String.valueOf(line.charAt(0)));
            operationService.ifPresent(service -> service.doOperation(line));
        });
    }
}
