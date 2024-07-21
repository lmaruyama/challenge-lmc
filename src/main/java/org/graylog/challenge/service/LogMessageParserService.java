package org.graylog.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graylog.challenge.model.ClientLogMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class LogMessageParserService {

    private final ObjectMapper objectMapper;

    public List<ClientLogMessage> parseLines(List<String> logLines) {
        final List<ClientLogMessage> messages = new ArrayList<>();
        logLines.forEach(
                line -> messages.add(parseLine(line))
        );
        messages.removeAll(Collections.singleton(null));

        log.info("{} out of {} messages parsed", messages.size(), logLines.size());
        return messages;
    }

    /*
        Invalid lines will not be processed
     */
    private ClientLogMessage parseLine(String line) {
        try {
            return objectMapper.readValue(line, ClientLogMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Line parsing error. Line content [{}]. Error: {}", line, e.getMessage());
        }

        return null;
    }
}
