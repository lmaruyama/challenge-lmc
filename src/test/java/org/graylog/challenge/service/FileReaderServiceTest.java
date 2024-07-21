package org.graylog.challenge.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(FileReaderService.class)
class FileReaderServiceTest {

    @Autowired
    FileReaderService fileReaderService;

    @MockBean
    FileValidatorService fileValidatorService;

    @Test
    void readFileWithInvalidLine_mustReturnAllLines() throws URISyntaxException, AccessDeniedException, FileNotFoundException {
        final Path path = Path.of(getClass().getResource("/test-sample-messages-invalid-line.txt").toURI());
        final File validFile = path.toFile();
        when(fileValidatorService.validate(validFile)).thenReturn(path);
        final List<String> lines = fileReaderService.readFile(validFile);
        assertEquals(3, lines.size());
    }
}
