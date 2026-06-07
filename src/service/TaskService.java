package service;
import java.util.ArrayList;
import java.util.UUID;

import model.Task;
import model.TaskStatus;

public class TaskService extends TrackerService<Task>{
	
	public TaskService() {
		super();
	}
	
	/*
	 * add a task
	 * @param task a new task
	 * @return TrackerServiceResult
	 */
	public TrackerServiceResult addTask(Task task) {
		return addItem(task);
	}
	
	/*
	 * delete a task by id
	 * @param id of the task
	 * @return TrackerServiceResult
	 */
	public TrackerServiceResult deleteTaskById(UUID id) {
		return deleteItemById(id);
	}
	
	/*
	 * get a task by id
	 * @param id of the task
	 * @return Task
	 */
	public Task getTaskById(UUID id) {
		return findItemById(id);
	}
	
	/*
	 * get all tasks
	 * @return ArrayList of tasks
	 */
	public ArrayList<Task> getAllTasks() {
		return getAllItems();
	}
	
	/*
	 * sort the list by due date
	 * @return TaskServiceResult
	 */
	public TrackerServiceResult sortTasksByDueDate() {
		if (itemList.isEmpty()) {
			return TrackerServiceResult.LIST_EMPTY;
		}
		
		itemList.sort((a, b) -> a.getDueDate().compareTo(b.getDueDate()));
		return TrackerServiceResult.SUCCESS;
	}
	
	/*
	 * get a filtered list by status
	 * @param status TaskStatus
	 * @require status not null
	 * @return filtered list by status
	 */
	public ArrayList<Task> getFilteredListByStatus(TaskStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("Status can't be null!");
		}
		
		ArrayList<Task> sortedList = new ArrayList<Task>();
		
		for (Task task : itemList) {
			if (task.getStatus() == status) {
				sortedList.add(task);
			}
		}
		return sortedList;
	}
	
	/*
	 * changes status of a task
	 * @param id of task
	 * @param status to be changed to
	 * @return TaskServiceResult
	 */
	public TrackerServiceResult changeStatusOfTask(UUID id, TaskStatus status) {
		if (id == null) {
			throw new IllegalArgumentException("Id can't be null!");
		}
		if (status == null) {
			throw new IllegalArgumentException("Status can't be null!");
		}
		
		for (Task task : itemList) {
			if (task.getId().equals(id)) {
				task.changeStatus(status);
				return TrackerServiceResult.SUCCESS;
			}
		}
		return TrackerServiceResult.NOT_IN_LIST;
	}
}
