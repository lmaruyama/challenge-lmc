package org.graylog.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileValidatorService {

    public Path validate(File file) throws FileNotFoundException, AccessDeniedException {

        if (file == null) {
            log.error("No file has been provided to be validate");
            throw new FileNotFoundException("No file has been provided to be validated");
        }

        final String filePath = file.getAbsolutePath();
        if (!file.isFile()) {
            log.error("The path provided is not from a valid file. Path [{}]", filePath);
            throw new FileNotFoundException("The path provided is not from a file. Path [" + filePath + "]");
        }

        if (!file.exists()) {
            log.error("The file provided does not exist. File [{}]", filePath);
            throw new FileNotFoundException("The file provided does not exist. File [" + filePath + "]");
        }

        if (!file.canRead()) {
            log.error("The file provided does not have permission to be read. File [{}]", filePath);
            throw new AccessDeniedException("The file provided cannot be read. Please change the file permissions. File [" + filePath + "]");
        }

        return Paths.get(filePath);
    }
}
