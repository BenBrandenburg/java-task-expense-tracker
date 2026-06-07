package service;
import java.util.ArrayList;
import java.util.UUID;

import model.Expense;
import model.ExpenseCategory;

public class ExpenseService extends TrackerService<Expense>{
	
	public ExpenseService() {
		super();
	}
	
	/*
	 * add a expense
	 * @param expense a new expense
	 * @return TrackerServiceResult
	 */
	public TrackerServiceResult addExpense(Expense expense) {
		return addItem(expense);
	}
	
	/*
	 * delete a expense by id
	 * @param id of the expense
	 * @return TrackerServiceResult
	 */
	public TrackerServiceResult deleteExpenseById(UUID id) {
		return deleteItemById(id);
	}
	
	/*
	 * get a expense by id
	 * @param id of the expense
	 * @return Expense
	 */
	public Expense getExpenseById(UUID id) {
		return findItemById(id);
	}
	
	/*
	 * get all expenses
	 * @return ArrayList of expense
	 */
	public ArrayList<Expense> getAllExpenses() {
		return getAllItems();
	}
	
	/*
	 * get a filtered list by category
	 * @param category ExpenseCategory
	 * @require category not null
	 * @return filtered list by category
	 */
	public ArrayList<Expense> getFilteredListByCategory(ExpenseCategory category) {
		if (category == null) {
			throw new IllegalArgumentException("Category can't be null!");
		}
		
		ArrayList<Expense> sortedList = new ArrayList<Expense>();
		
		for (Expense expense : itemList) {
			if (expense.getCategory() == category) {
				sortedList.add(expense);
			}
		}
		return sortedList;
	}
	
	/*
	 * sort expenses by value; hight to low
	 * @return TaskServiceResult
	 */
	public TrackerServiceResult sortExpensesByValue() {
		if (itemList.isEmpty()) {
			return TrackerServiceResult.LIST_EMPTY;
		}
		
		itemList.sort((a, b) -> (a.getValue() <= b.getValue() ? 1 : -1));
		return TrackerServiceResult.SUCCESS;
	}
	
	/*
	 * sort expenses by date; hight to low
	 * @return TaskServiceResult
	 */
	public TrackerServiceResult sortExpenseByDate() {
		if (itemList.isEmpty()) {
			return TrackerServiceResult.LIST_EMPTY;
		}
		
		itemList.sort((a, b) -> a.getDate().compareTo(b.getDate()));
		return TrackerServiceResult.SUCCESS;
	}
}
