package com.kodilla.hibernate.tasklist.dao;

import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.task.TaskFinancialDetails;
import com.kodilla.hibernate.tasklist.TaskList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {
    @Autowired
    private TaskListDao taskListDao;
    private static final String NAME = "Test List";
    private static final String DESCRIPTION = "Excercise: Learn Hibernate";

    @Test
    public void testFindByListName() {
        //Given
        TaskList taskList = new TaskList(NAME, DESCRIPTION);
        taskListDao.save(taskList);
        String name = taskList.getListName();

        //When
        List<TaskList> readTaskList = taskListDao.findByListName(name);

        //Then
        int id = taskList.getId();
        Assert.assertEquals(1, readTaskList.size());

        //CleanUp
        taskListDao.delete(taskList);
    }

    @Test
    public void testTaskListDaoSaveWithTasks() {
        //Given
        Task task = new Task("Test: Learn Hibernate", 14);
        Task task2 = new Task("Test: Write some entities", 3);
        TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
        TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
        task.setTaskFinancialDetails(tfd);
        task2.setTaskFinancialDetails(tfd2);
        TaskList taskList = new TaskList("List Name", "ToDo tasks");
        taskList.getTasks().add(task);
        taskList.getTasks().add(task2);
        task.setTaskList(taskList);
        task2.setTaskList(taskList);

        //When
        taskListDao.save(taskList);
        int id = taskList.getId();

        //Given
        Assert.assertEquals(5, id);

        //CleanUp
        //taskListDao.delete(id);
    }
}
