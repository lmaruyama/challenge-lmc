package org.graylog.challenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(FileValidatorService.class)
class FileValidatorServiceTest {

    @Autowired
    FileValidatorService fileValidatorService;

    @Test
    void validateNullFile_shouldThrowFileNotFoundException() {
        final String message = assertThrows(FileNotFoundException.class, () -> fileValidatorService.validate(null)).getMessage();
        assertTrue(message.contains("No file has been provided to be validated"));
    }

    @Test
    void validateValidFile_shouldReturnPath() throws AccessDeniedException, FileNotFoundException, URISyntaxException {
        final Path path = Path.of(getClass().getResource("/test-sample-messages.txt").toURI());
        File validFile = path.toFile();
        final Path returnedPath = fileValidatorService.validate(validFile);
        assertNotNull(returnedPath);
    }

    @Test
    void validateDirectory_shouldThrownFileNotFoundException() throws URISyntaxException {
        File folder = new File("src/test/resources");
        final String message = assertThrows(FileNotFoundException.class, () -> fileValidatorService.validate(folder)).getMessage();
        assertTrue(message.contains("The path provided is not from a file"));
    }
}
