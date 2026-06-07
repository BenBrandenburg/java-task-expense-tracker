package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import model.Task;
import model.TaskStatus;

public class TaskServiceTest {

	private TaskService service;
	
	private Task task1;
	private Task task2;
	
	public TaskServiceTest() {
	    service = new TaskService();
	
	    task1 = new Task(
	            "GS homework",
	            "Start bitmap",
	            LocalDate.now().plusDays(5),
	            TaskStatus.NOT_STARTED);
	
	    task2 = new Task(
	            "Job applications",
	            "Send out applications",
	            LocalDate.now().plusDays(14),
	            TaskStatus.IN_PROGRESS);
	}
	
	@Test
	public void testAddTask() {
	    assertEquals(TrackerServiceResult.SUCCESS, service.addTask(task1));
	
	    assertEquals(1, service.getAllTasks().size());
	}
	
	@Test
	public void testAddDuplicateTask() {
	    service.addTask(task1);
	
	    assertEquals(TrackerServiceResult.ALREADY_IN_LIST, service.addTask(task1));
	}
	
	@Test
	public void testDeleteTask() {
	    service.addTask(task1);
	
	    assertEquals(TrackerServiceResult.SUCCESS, service.deleteTaskById(task1.getId()));
	
	    assertEquals(0, service.getAllTasks().size());
	}
	
	@Test
	public void testGetTaskById() {
	    service.addTask(task1);
	
	    assertEquals(task1, service.getTaskById(task1.getId()));
	}
	
	@Test
	public void testChangeStatusOfTask() {
	    service.addTask(task1);
	
	    service.changeStatusOfTask(task1.getId(),TaskStatus.COMPLETED);
	
	    assertEquals(TaskStatus.COMPLETED,task1.getStatus());
	}
	
	@Test
	public void testGetFilteredListByStatus() {
	    service.addTask(task1);
	    service.addTask(task2);
	
	    assertEquals(1, service.getFilteredListByStatus(TaskStatus.NOT_STARTED).size());
	}
	
	@Test
	public void testSortTasksByDueDate() {
	    service.addTask(task2);
	    service.addTask(task1);
	
	    service.sortTasksByDueDate();
	
	    assertEquals(task1,service.getAllTasks().get(0));
	}

}

