package com.javatab.services;

import java.util.concurrent.Future;

/**
 * Created by nasir on 26/1/16.
 */
public interface MailSenderService {

    Future<Boolean> sendAsyncMailWithResult() throws InterruptedException;
}
