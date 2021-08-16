package com.javatab.web;

import com.javatab.AbstractTodoControllerTests;
import com.javatab.models.Task;
import com.javatab.services.TodoService;
import com.javatab.web.apis.TodoController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nasir on 26/1/16.
 */
public class TodoControllerTests extends AbstractTodoControllerTests {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(todoController);
    }

    @Test
    public void testGetTasks() throws Exception {

        List<Task> tasks = getListOfStubData();

        when(todoService.getAllTasks()).thenReturn(tasks);

        String uri = "/api/todo";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(todoService, times(1)).getAllTasks();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a ",
                content.trim().length() > 0);
    }

    public List<Task> getListOfStubData() {

        List<Task> listOfStubData = new ArrayList<>();
        listOfStubData.add(getStubData());
        return listOfStubData;
    }

    public Task getStubData() {

        Task aTask = new Task();
        aTask.setId(1);
        aTask.setTask("task 1");
        return aTask;
    }
}
