package org.graylog.challenge.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientLogMessageTest {

    @Test
    void testDeserialization_mustReturnValidGLEFPayload() {
        ClientLogMessage clientLogMessage = new ClientLogMessage();
        clientLogMessage.setTimestamp(1385053862.3072);
        clientLogMessage.setShortMessage("Access to the Graylog search");
        clientLogMessage.setClientDeviceType("desktop");
        System.out.println(clientLogMessage);

        final String expected = """
                {"version":"1.1","level":1,"timestamp":1385053862.3072,"short_message":"Access to the Graylog search","_ClientDeviceType":"desktop""";

        assertThat(clientLogMessage.toString()).contains(expected);
    }

    @Test
    void testParseFileLineInput_mustReturnTheRespectiveValues() throws JsonProcessingException {
        String input = """
                {
                    "ClientDeviceType": "desktop",
                    "ClientIP": "192.168.87.52",
                    "ClientIPClass": "noRecord",
                    "ClientStatus": 403,
                    "ClientRequestBytes": 889,
                    "ClientRequestReferer": "graylog.org",
                    "ClientRequestURI": "/search",
                    "ClientRequestUserAgent": "Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",
                    "ClientSrcPort":122,
                    "EdgeServerIP": "10.0.151.71",
                    "EdgeStartTimestamp": 1576929197,
                    "DestinationIP": "172.16.153.30",
                    "OriginResponseBytes": 821,
                    "OriginResponseTime": 337000000}
                """;

        ObjectMapper mapper = new ObjectMapper();
        final ClientLogMessage clientLogMessage = mapper.readValue(input, ClientLogMessage.class);

        assertThat(clientLogMessage.getVersion()).isEqualTo(GelfHttpMessage.GELF_VERSION);
        assertThat(clientLogMessage.getLevel()).isEqualTo(GelfHttpMessage.GELF_DEFAULT_LEVEL_ALERT);

        assertThat(clientLogMessage.getClientDeviceType()).isEqualTo("desktop");
        assertThat(clientLogMessage.getClientIP()).isEqualTo("192.168.87.52");
        assertThat(clientLogMessage.getClientIPClass()).isEqualTo("noRecord");
        assertThat(clientLogMessage.getClientStatus()).isEqualTo(403);
        assertThat(clientLogMessage.getClientRequestBytes()).isEqualTo(889);
        assertThat(clientLogMessage.getClientRequestReferer()).isEqualTo("graylog.org");
        assertThat(clientLogMessage.getClientRequestURI()).isEqualTo("/search");
        assertThat(clientLogMessage.getClientRequestUserAgent()).isEqualTo("Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");
        assertThat(clientLogMessage.getClientSrcPort()).isEqualTo(122);
        assertThat(clientLogMessage.getEdgeServerIP()).isEqualTo("10.0.151.71");
        assertThat(clientLogMessage.getEdgeStartTimestamp()).isEqualTo(1576929197);
        assertThat(clientLogMessage.getDestinationIP()).isEqualTo("172.16.153.30");
        assertThat(clientLogMessage.getOriginResponseBytes()).isEqualTo(821);
        assertThat(clientLogMessage.getOriginResponseTime()).isEqualTo(337000000);

    }
}
