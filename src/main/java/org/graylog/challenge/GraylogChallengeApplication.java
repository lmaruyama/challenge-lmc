package org.graylog.challenge;

import org.graylog.challenge.service.LogProcessor;
import org.graylog.challenge.service.LogProcessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraylogChallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GraylogChallengeApplication.class, args);
	}

	LogProcessorService service;

	public GraylogChallengeApplication(LogProcessorService service) {
		this.service = service;
	}

	@Override
	public void run(String... args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Only 2 arguments must be present. E.g.: " +
					"<protocol>://<server>[:port]/<api> /path/to/file.txt");
		}

		final String graylogServer = args[0];
		final String filePath = args[1];

		LogProcessor logProcessor = new LogProcessor.Builder()
                .withServer(graylogServer)
				.withFilePath(filePath)
				.build();

		service.send(logProcessor);
	}
}
