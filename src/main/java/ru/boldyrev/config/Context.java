package ru.boldyrev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.boldyrev.parsing.MarathonParser;
import ru.boldyrev.task.Task;

import java.net.URL;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Configuration
public class Context {

    public static final String MONITORING_INTERVAL = "monitoringIntervalSeconds";

    @Bean(name = MONITORING_INTERVAL)
    public Integer delay(@Value("${" + MONITORING_INTERVAL + "}") Integer interval) {
        return interval;
    }

    @Bean
    public Task marathonTask(@Value("${address.marathon}") String marathonAddress, MarathonParser marathonParser) {
        try {
            URL marathonUrl = new URL(marathonAddress);
            return new Task(marathonUrl, marathonParser);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании URL'а для марафона, адрес " + marathonAddress, e);
        }
    }

}
