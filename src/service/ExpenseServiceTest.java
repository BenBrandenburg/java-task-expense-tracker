package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Expense;
import model.ExpenseCategory;

public class ExpenseServiceTest {

	private ExpenseService service;
	
	private Expense exp1;
	private Expense exp2;
	
	public ExpenseServiceTest() {
	    service = new ExpenseService();
	
	    exp1 = new Expense(
	            "Rent",
	            "",
	            ExpenseCategory.RENT,
	            647.5);
	
	    exp2 = new Expense(
	            "Lunch",
	            "McDonalds",
	            ExpenseCategory.FOOD,
	            17.5);
	}
	
	@Test
	public void testAddExpense() {
	    assertEquals(TrackerServiceResult.SUCCESS,service.addExpense(exp1));
	
	    assertEquals(1, service.getAllExpenses().size());
	}
	
	@Test
	public void testAddDuplicateExpense() {
	    service.addExpense(exp1);
	
	    assertEquals(TrackerServiceResult.ALREADY_IN_LIST, service.addExpense(exp1));
	}
	
	@Test
	public void testDeleteExpense() {
	    service.addExpense(exp1);
	
	    assertEquals(TrackerServiceResult.SUCCESS, service.deleteExpenseById(exp1.getId()));
	
	    assertEquals(0, service.getAllExpenses().size());
	}
	
	@Test
	public void testGetExpenseById() {
	    service.addExpense(exp1);
	
	    assertEquals(exp1, service.getExpenseById(exp1.getId()));
	}
	
	@Test
	public void testGetFilteredListByCategory() {
	    service.addExpense(exp1);
	    service.addExpense(exp2);
	
	    assertEquals(1,service.getFilteredListByCategory(ExpenseCategory.RENT).size());
	}
	
	@Test
	public void testSortExpensesByValue() {
	    service.addExpense(exp1);
	    service.addExpense(exp2);
	
	    service.sortExpensesByValue();
	
	    assertEquals(exp1,service.getAllExpenses().get(0));
	}
	
	@Test
	public void testSortExpensesByDate() {
	    service.addExpense(exp1);
	    service.addExpense(exp2);
	
	    assertEquals(TrackerServiceResult.SUCCESS,service.sortExpenseByDate());
	}


}
