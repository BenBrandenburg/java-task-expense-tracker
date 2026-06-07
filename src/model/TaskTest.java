package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class TaskTest {
	private static final String title1 = "GS homework";
	private static final String desc1 = "Start bitmap";
	private static final LocalDate dueDate1 = LocalDate.now().plusDays(5);
	private static final TaskStatus status1 = TaskStatus.NOT_STARTED;
	private Task task1;
	
	private static final String title2 = "Job applications";
	private static final String desc2 = "Send out applications to ...";
	private static final LocalDate dueDate2 = LocalDate.now().plusDays(14);
	private static final TaskStatus status2 = TaskStatus.IN_PROGRESS;
	private Task task2;
	
	
	public TaskTest() {
		task1 = new Task(title1, desc1, dueDate1, status1);
		task2 = new Task(title2, desc2, dueDate2, status2);
	}
	
	
	@Test
	public void testConstructor() {
		assertEquals(task1.getTitle(), title1);
		assertEquals(task1.getDescription(), desc1);
		assertEquals(task1.getCreatedDate(), LocalDate.now());
		assertEquals(task1.getDueDate(), dueDate1);
		assertEquals(task1.getStatus(), status1);
	}
	
	@Test
	public void testChangeTitle() {
		String newTitle = "AF homework";
		task1.changeTitle(newTitle);
		assertEquals(task1.getTitle(), newTitle);
	}
	
	@Test
	public void testChangeDescription() {
		String newDesc = "Start finite state machine";
		task1.changeDescription(newDesc);
		assertEquals(task1.getDescription(), newDesc);
	}
	
	@Test
	public void testChangeDueDate() {
		LocalDate newDueDate = task1.getDueDate().plusDays(5);
		task1.changeDueDate(newDueDate);
		assertEquals(task1.getDueDate(), newDueDate);
	}
	
	@Test
	public void testChangeStatus() {
		TaskStatus newStatus = TaskStatus.COMPLETED;
		task1.changeStatus(newStatus);
		assertEquals(task1.getStatus(), newStatus);
	}
	
	@Test
	public void testEqual() {
		assertTrue(task1.equals(task1));
		assertFalse(task1.equals(task2));
	}
}