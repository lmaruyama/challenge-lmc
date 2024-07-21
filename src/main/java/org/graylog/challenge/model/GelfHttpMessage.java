package org.graylog.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.graylog.challenge.serializer.GelfTimestampSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class GelfHttpMessage {

    static final String GELF_VERSION = "1.1";
    static final int GELF_DEFAULT_LEVEL_ALERT = 1;

    /**
     * GELF spec version – “1.1”;
     * MUST be set by the client library.
     */
    @JsonProperty
    private String version = GELF_VERSION;
    /**
     * the name of the host, source or application that sent this message;
     * MUST be set by the client library.
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String host;
    /**
     * a short, descriptive message;
     * MUST be set by the client library.
     */
    @JsonProperty(value = "short_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shortMessage;
    /**
     * a long message that can contain a backtrace; optional.
     */
    @JsonProperty("full_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullMessage;
    /**
     * the level equal to the standard syslog levels; optional. Default is 1 (ALERT).
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int level = GELF_DEFAULT_LEVEL_ALERT;

    /**
     * seconds since UNIX epoch with optional decimal places for milliseconds;
     * SHOULD be set by the client library.
     * If absent, the timestamp will be set to the current time (now).
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = GelfTimestampSerializer.class)
    private Double timestamp;

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize GELF Message", e);
        }
    }
}
