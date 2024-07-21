package org.graylog.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.graylog.challenge.model.ClientLogMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig({LogMessageParserService.class, ObjectMapper.class})
class LogMessageParserServiceTest {

    @Autowired
    LogMessageParserService logMessageParserService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void parseLinesWithInvalid_mustNotReturnTheInvalidLine() {

        List<String> lines = List.of(
                """
                        {"ClientDeviceType": "desktop","ClientIP": "192.168.87.52","ClientIPClass": "noRecord","ClientStatus": 403, "ClientRequestBytes": 889,"ClientRequestReferer": "graylog.org","ClientRequestURI": "/search","ClientRequestUserAgent": "Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko","ClientSrcPort":122,"EdgeServerIP": "10.0.151.71","EdgeStartTimestamp": 1576929197,"DestinationIP": "172.16.153.30","OriginResponseBytes": 821,"OriginResponseTime": 337000000}
                        """,
                """
                        {"ClientDeviceType": "desktop","ClientIP": "192.168.87.52"
                        """,
                """
                        {"ClientDeviceType": "mobile","ClientIP": "192.168.87.30","ClientIPClass": "noRecord","ClientStatus": 200, "ClientRequestBytes": 794,"ClientRequestReferer": "graylog.com","ClientRequestURI": "/search","ClientRequestUserAgent": "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)","ClientSrcPort":420,"EdgeServerIP": "10.0.41.217","EdgeStartTimestamp": 1576929197,"DestinationIP": "172.16.147.227","OriginResponseBytes": 697,"OriginResponseTime": 296000000}
                        """
        );

        final List<ClientLogMessage> clientLogMessages = logMessageParserService.parseLines(lines);
        assertEquals(2, clientLogMessages.size());
    }
}
