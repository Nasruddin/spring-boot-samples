package com.javatab.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by nasir on 26/1/16.
 */
@Component
public class MailSenderServiceImpl implements MailSenderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @Override
    public Future<Boolean> sendAsyncMailWithResult() throws InterruptedException {

        logger.info("Sending mail...");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {

            String thread = Thread.currentThread().getName();
            logger.info("Current thread : {}", thread);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("Interrupted");
            }
        });

        return new AsyncResult<>(true);
    }
}
