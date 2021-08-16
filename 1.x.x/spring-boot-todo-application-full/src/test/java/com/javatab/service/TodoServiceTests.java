package com.javatab.service;

import com.javatab.AbstractTodoTests;
import com.javatab.models.Task;
import com.javatab.services.TodoService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nasir on 26/1/16.
 */
public class TodoServiceTests extends AbstractTodoTests{

    @Autowired
    private TodoService todoService;

    @Before
    public void setUp() throws Exception {
        todoService.evictCache();
    }

    @Test
    public void testGetAllTasks() throws Exception {

        List<Task> tasks = todoService.getAllTasks();

        logger.info("{} value", tasks);

        Assert.assertNotNull("failure - excepted not null", tasks);
        Assert.assertTrue("failure - expected size greater than 0", tasks.size() > 0);
    }

    @After
    public void tearDown() throws Exception {


    }
}
