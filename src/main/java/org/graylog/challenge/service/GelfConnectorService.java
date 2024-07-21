package org.graylog.challenge.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.graylog.challenge.model.ClientLogMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Service
@AllArgsConstructor
@Slf4j
public class GelfConnectorService {

    private final OkHttpClient httpClient;

    public void send(String server, List<ClientLogMessage> messages) {
        final List<String> translated = translateAll(messages);
        if (translated.isEmpty()) {
            log.warn("No log messages found.");
            return;
        }

        Request request = new Request.Builder()
                .url(server)
                .post(
                    RequestBody.create(
                        translated
                                .stream()
                                .reduce("", (a, b) -> a + System.lineSeparator() + b),
                        MediaType.parse(APPLICATION_JSON_VALUE)))
                .build();

        try (final Response response = httpClient.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                log.warn("GELF API call failed: {} - {}", response.code(), response.message());
            } else {
                log.info("GELF processed the log messages successfully.");
            }
        } catch (IOException e) {
            log.error("It was not possible to create the connection to {}. Error: {}", server, e.getMessage());
        }
    }

    private List<String> translateAll(List<ClientLogMessage> messages) {
        List<String> translatedLines = new ArrayList<>();
        messages.forEach(
                message -> translatedLines.add(translateLine(message))
        );
        return translatedLines;
    }

    /*
        This can be modified to populate the
        GELF default payload with the client's log information
     */
    private String translateLine(ClientLogMessage message) {
        message.setHost(message.getClientRequestReferer());
        message.setShortMessage("Access to " + message.getClientRequestReferer() + message.getClientRequestURI() + " from " + message.getClientIP());
        message.setTimestamp(message.getEdgeStartTimestamp());

        return message.toString();
    }
}
