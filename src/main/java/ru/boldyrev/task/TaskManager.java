package ru.boldyrev.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.boldyrev.config.Context;
import ru.boldyrev.db.DbManager;
import ru.boldyrev.db.entity.BetType;
import ru.boldyrev.db.entity.Game;
import ru.boldyrev.db.entity.GameTime;
import ru.boldyrev.db.repository.BetTypeRepository;
import ru.boldyrev.db.repository.GameRepository;
import ru.boldyrev.db.repository.GameTimeRepository;
import ru.boldyrev.http.PageManager;
import ru.boldyrev.parsing.ParseResult;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Service
public class TaskManager {

    private static final Logger log = LoggerFactory.getLogger(TaskManager.class);
    @Autowired private PageManager pageManager;
    @Autowired private DbManager dbManager;
    @Autowired private List<Task> taskList;
    @Value("${" + Context.MONITORING_INTERVAL + "}") private Integer monitoringInterval;

    @PostConstruct
    public void runScheduler() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable action = () -> taskList.parallelStream().forEach(this::runTask);
        executorService.scheduleAtFixedRate(action, 5, monitoringInterval, TimeUnit.SECONDS);
    }

    private void runTask(Task task) {
        try {
            String pageContent = pageManager.loadPageContent(task.getUrl());
            List<ParseResult> parseResult = task.getParser().parsePage(pageContent);
            dbManager.save(parseResult);
        } catch (Exception e) {
            log.error("Ошибка при выполнении задачи " + task, e);
        }
    }

}
