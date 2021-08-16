package com.javatab.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by nasruddin on 18/6/16.
 */
@Component
public class BatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0,30 * * * * *")
    public void batchJob() {

        LocalTime time = LocalTime.now();

        logger.info("Job is called at: " + time );
    }

}
