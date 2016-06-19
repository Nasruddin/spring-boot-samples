package com.javatab.web.apis;

import com.javatab.models.Task;
import com.javatab.services.MailSenderService;
import com.javatab.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by nasir on 25/1/16.
 */
@RestController
public class TodoController extends BaseController{

    private TodoService todoService;

    private MailSenderService mailSenderService;

    // To fetch the number of times a method is called
    private CounterService counterService;

    @Autowired
    public TodoController(TodoService todoService, MailSenderService mailSenderService, CounterService counterService) {
        this.todoService = todoService;
        this.mailSenderService = mailSenderService;
        this.counterService = counterService;
    }

    @RequestMapping(
            value = "/api/todo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getAllTask() {

        counterService.increment("invoked.todoService.getAllTasks");

        List<Task> tasks = todoService.getAllTasks();

        if (tasks.size() <= 0) {
            logger.info("Data is empty? No task found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/todo/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable("id") long id) {

        counterService.increment("invoked.todoService.getTask");

        Task task = todoService.getTaskById(id);

        if (task == null) {
            logger.info("Unable to fetch task with id - {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/todo/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        counterService.increment("invoked.todoService.createTask");

        if (task.getTask() == null || StringUtils.isEmpty(task.getTask())) {
            logger.info("Unable to create task with empty task", task);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Task savedTask = todoService.createTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/todo/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {

        counterService.increment("invoked.todoService.deleteTask");

        Task task = todoService.getTaskById(id);
        if (task == null) {
            logger.info("Unable to find task with id - {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/api/todo/{id}/send",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> sendGreeting(@PathVariable("id") Long id,
                                                 @RequestParam(
                                                         value = "wait",
                                                         defaultValue = "true") boolean waitForAsyncResult) {


        Task task = null;

        try {
            task = todoService.getTaskById(id);

            if (task == null) {
                logger.info(" sending mail with todo id:{}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (waitForAsyncResult) {
                Future<Boolean> asyncResponse = mailSenderService.sendAsyncMailWithResult();
                boolean emailSent = asyncResponse.get();
                logger.info("todo email sent? {}", emailSent);
            } else {
                //Call async method without result
            }
        } catch (Exception e) {
            logger.error("A problem occurred sending the Greeting.", e);
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< send mail with todo id:{}", id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
