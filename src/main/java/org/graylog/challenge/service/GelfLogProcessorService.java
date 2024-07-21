package org.graylog.challenge.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graylog.challenge.exception.LogMessageProcessorException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;

@Slf4j
@Service
@AllArgsConstructor
public class GelfLogProcessorService implements LogProcessorService {

    private final FileReaderService fileReaderService;
    private final LogMessageParserService logMessageParserService;
    private final GelfConnectorService connectorService;

    @Override
    public void send(LogProcessor processor) {
        try {
            String server = processor.getServer();
            File file = new File(processor.getFilePath());
            var logLines = fileReaderService.readFile(file);
            var messages = logMessageParserService.parseLines(logLines);

            connectorService.send(server, messages);

        } catch (FileNotFoundException | AccessDeniedException e) {
            log.error("Error while reading file. Reason: {}", e.getMessage() , e);
            throw new LogMessageProcessorException(e.getMessage());
        }
    }
}
