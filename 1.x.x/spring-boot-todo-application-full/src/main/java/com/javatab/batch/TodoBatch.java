package com.javatab.batch;

import com.javatab.models.Task;
import com.javatab.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nasir on 26/1/16.
 */
@Profile("batch")
@Component
public class TodoBatch {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private TodoService todoService;

    @Scheduled(
            cron = "${batch.greeting.cron}")
    public void cronJob() {

        logger.info("> cronJob");

        //Add scheduled logic
        List<Task> greetings = todoService.getAllTasks();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< cronJob");

    }

    @Scheduled(
            initialDelayString = "${batch.greeting.initialDelay}",
            fixedRateString = "${batch.greeting.fixedDelay}")
    public void reportCurrentTime() {
        logger.info("The time is now " + dateFormat.format(new Date()));
    }
}
