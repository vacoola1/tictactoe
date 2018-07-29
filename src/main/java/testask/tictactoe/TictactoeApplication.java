package testask.tictactoe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@SpringBootApplication
@Configuration
@Slf4j
public class TictactoeApplication {
	private static final int JDBC_PULL_SIZE = 20;

	@Bean
	public Scheduler jdbcScheduler() {
		return Schedulers.fromExecutor(Executors.newFixedThreadPool(JDBC_PULL_SIZE));
	}

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}
}
