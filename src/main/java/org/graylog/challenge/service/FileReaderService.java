package org.graylog.challenge.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graylog.challenge.exception.FileReaderException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class FileReaderService {

    private final FileValidatorService fileValidatorService;

    public List<String> readFile(File file) throws FileNotFoundException, AccessDeniedException {

        final Path path = fileValidatorService.validate(file);

        try (Stream<String> stream = Files.lines(path)) {
            final List<String> lines = stream.toList();
            log.info("{} lines found on the file [{}]", lines.size(), file.getName());
            return lines;
        } catch (IOException e) {
            log.error("Error while reading file [{}]", file.getName(), e);
            throw new FileReaderException("It was not possible to proceed with you request.");
        }
    }
}
