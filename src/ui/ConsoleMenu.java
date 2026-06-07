package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Expense;
import model.ExpenseCategory;
import model.Task;
import model.TaskStatus;
import model.TrackerItem;
import service.TaskService;
import service.TrackerServiceResult;
import service.ExpenseService;

public class ConsoleMenu {
	private Scanner scanner;
	
	private TaskService taskService;
	private ExpenseService expenseService;
	
	public ConsoleMenu () {
		scanner = new Scanner(System.in);
	    taskService = new TaskService();
	    expenseService = new ExpenseService();
	}
	
	public void run() {
		int input = 9;
		while(input != 0) {
			printMenu();
			input = waitForMenuInput();
			execute(input);
		}
	}
		
	private void printMenu() {
		System.out.println("Choose an action:");
		System.out.println("[1]: Add task"); 
		System.out.println("[2]: Delete task"); 
		System.out.println("[3]: List tasks");
		System.out.println("[4]: Change task status"); 
		System.out.println("[5]: Add expense"); 
		System.out.println("[6]: Delete expense"); 
		System.out.println("[7]: List expenses"); 
		System.out.println("[8]: Show expenses by category");
		System.out.println("[0]: Exit"); 
	}
	
	private int waitForMenuInput() {
		return Integer.parseInt(scanner.nextLine());
	}
	
	private void execute(int input) {
		TrackerServiceResult result = TrackerServiceResult.SUCCESS;
		
		switch(input) {
			case 1,5: result = addItem(input); break;
			case 2: result = deleteTask(); break;
			case 3: listItems(taskService.getAllTasks()); break;
			case 4: result = changeTaskStatus(); break;
			case 6: result = deleteExpense(); break;
			case 7: listItems(expenseService.getAllExpenses()); break;
			case 8: listExpenseByCategory(); break;
			case 0: System.out.println("Exited application!"); return;
			default:
		}
		
		switch(result) {
			case TrackerServiceResult.INVALID_INPUT: System.out.println("Invalid input!"); break;
			case TrackerServiceResult.NOT_IN_LIST: System.out.println("Not in list!"); break;
			case TrackerServiceResult.ALREADY_IN_LIST: System.out.println("Already in list!"); break;
			case TrackerServiceResult.LIST_EMPTY: System.out.println("List empty!"); break;
			case TrackerServiceResult.SUCCESS: System.out.println("Operation was successful."); break;
			default:
		}
		
		System.out.println();
		System.out.println("Press ENTER to continue...");
		scanner.nextLine();
	}
	
	
	private TrackerServiceResult addItem(int input) {
		String title;
		String description;
		
		System.out.println("Title:");
		title = scanner.nextLine();
		
		System.out.println("Description:");
		description = scanner.nextLine();
		
		if (input == 1) {
			return addTask(title, description);
		}
		else {
			return addExpense(title, description);
		}
		
		
	}
	
	private TrackerServiceResult addTask(String title, String description) {
		LocalDate dueDate;
		
		System.out.println("Due date (Year-Month-Day):");
		dueDate = LocalDate.parse(scanner.nextLine());
		
		return taskService.addTask(new Task(title, description, dueDate, TaskStatus.NOT_STARTED));
	}
	
	private TrackerServiceResult addExpense(String title, String description) {
		double value;
		ExpenseCategory category = null;
		
		System.out.println("Value in €: ");
		value = Double.parseDouble(scanner.nextLine());
		
		while (category == null) {
			System.out.println("Category (food, transport, rent, shopping)");
			String input = scanner.nextLine();
			switch(input) {
				case "food": category = ExpenseCategory.FOOD; break;
				case "transport": category = ExpenseCategory.TRANSPORT; break;
				case "rent": category = ExpenseCategory.RENT; break;
				case "shopping": category = ExpenseCategory.SHOPPING; break;
				default: System.out.println("This is not a valid category!");
			}
		}
		
		return expenseService.addExpense(new Expense(title, description, category, value));
	}
	
	private TrackerServiceResult deleteTask() {
		int index;
		ArrayList<Task> tasks = taskService.getAllTasks();
		
		index = chooseItemByNumber(tasks) -1;
		
		if (index < 0 || index >= tasks.size()) {
			System.out.println("Invalid task number!");
			return TrackerServiceResult.INVALID_INPUT;
		}
		
		Task selectedTask = tasks.get(index);
		return taskService.deleteTaskById(selectedTask.getId());
		
	}
	
	private TrackerServiceResult deleteExpense() {
		int index;
		ArrayList<Expense> expenses = expenseService.getAllExpenses();
		
		index = chooseItemByNumber(expenses) -1;
		
		if (index < 0 || index >= expenses.size()) {
			System.out.println("Invalid expense number!");
			return TrackerServiceResult.INVALID_INPUT;
		}
		
		Expense selectedExpense = expenses.get(index);
		return expenseService.deleteExpenseById(selectedExpense.getId());
	}
	
	private TrackerServiceResult changeTaskStatus() {
		int index;
		ArrayList<Task> tasks = taskService.getAllTasks();
		
		index = chooseItemByNumber(tasks) -1; 
		
		if (index < 0 || index >= tasks.size()) {
			System.out.println("Invalid task number!");
			return TrackerServiceResult.INVALID_INPUT;
		}
		
		System.out.println("Write new Status (not started, in progress, completed)");
		String input = scanner.nextLine();
		TaskStatus status;
		switch(input) {
			case "not started": status = TaskStatus.NOT_STARTED; break;
			case "in progress": status = TaskStatus.IN_PROGRESS; break;
			case "completed":   status = TaskStatus.COMPLETED; break;
			default: status = TaskStatus.NOT_STARTED;
		}
		
		Task selectedTask = tasks.get(index);
		return taskService.changeStatusOfTask(selectedTask.getId(), status);
		
	}
	
	private void listExpenseByCategory() {
		System.out.print("Choose a category: ");
		System.out.println("[1] Food ");
		System.out.println("[2] Transport ");
		System.out.println("[3] Rent ");
		System.out.println("[4] Shopping");
		
		int input = Integer.parseInt(scanner.nextLine());
		ExpenseCategory category;
		
		switch (input) {
			case 1: category = ExpenseCategory.FOOD; break;
			case 2: category = ExpenseCategory.TRANSPORT; break;
			case 3: category = ExpenseCategory.RENT; break;
			case 4: category = ExpenseCategory.SHOPPING; break;
			default: System.out.println("This is not a valid category!"); return;
		}
		
		ArrayList<Expense> expenseListByCategory = expenseService.getFilteredListByCategory(category);
		listItems(expenseListByCategory);
	}
	
	private void listItems(ArrayList<? extends TrackerItem> items) {
		if (items.isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		
		int i = 1;
		
		for (TrackerItem item : items) {
			System.out.print("[" + i + "]" + ". ");
			System.out.println(item.getTitle());
			System.out.println(item.getDescription());
			
			if (item instanceof Task) {
				Task task = (Task) item;
				System.out.println("Due date: " + task.getDueDate());
				System.out.println("Status: " + task.getStatus());
				i ++;
			}
			else if (item instanceof Expense) {
				Expense expense = (Expense) item;
				System.out.println("Value in €: " + expense.getValue());
				System.out.println("Category: " + expense.getCategory());
				System.out.println("Date: " + expense.getDate());
				i ++;
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int chooseItemByNumber(ArrayList<? extends TrackerItem> items) {
		if (items.isEmpty()) {
	        System.out.println("No items available.");
	        return 0;
	    }
		
		listItems(items);
		System.out.println("Choose items number: ");
		int choice = Integer.parseInt(scanner.nextLine());
		return choice;
	}
}
