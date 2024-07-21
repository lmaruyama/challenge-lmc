package org.graylog.challenge.service;

import lombok.Getter;

@Getter
public class LogProcessor {

    private final String server;
    private final String filePath;

    private LogProcessor(String server, String filePath) {
        this.server = server;
        this.filePath = filePath;
    }

    public static class Builder {
        private String server;
        private String filePath;

        public LogProcessor build() {
            return new LogProcessor(server, filePath);
        }

        public Builder withServer(String graylogServer) {
            this.server = graylogServer;
            return this;
        }

        public Builder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }
    }
}


