package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ExpenseTest {
	private static final String title1 = "Rent";
	private static final String desc1 = "";
	private static final ExpenseCategory category1 = ExpenseCategory.RENT;
	private static final double value1 = 647.5;
	private Expense exp1;
	
	private static final String title2 = "Lunch";
	private static final String desc2 = "McDonalds";
	private static final ExpenseCategory category2 = ExpenseCategory.FOOD;
	private static final double value2 = 17.5;
	private Expense exp2;
	
	
	public ExpenseTest() {
		exp1 = new Expense(title1, desc1, category1, value1);
		exp2 = new Expense(title2, desc2, category2, value2);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(exp1.getTitle(), title1);
		assertEquals(exp1.getDescription(), desc1);
		assertEquals(exp1.getCategory(), category1);
		assertEquals(value1, exp1.getValue(), 0.001);
		assertEquals(exp1.getDate(), LocalDate.now());
	}
	
	@Test
	public void testChangeTitle() {
		String newTitle = "Rent2";
		exp1.changeTitle(newTitle);
		assertEquals(exp1.getTitle(), newTitle);
	}
	
	@Test
	public void testChangeDescription() {
		String newDesc = "Rent for apartment.";
		exp1.changeDescription(newDesc);
		assertEquals(exp1.getDescription(), newDesc);
	}
	
	@Test
	public void testChangeValue() {
		double newValue = 120.5;
		exp1.changeValue(newValue);
		assertEquals(exp1.getValue(), newValue, 0.001);
	}
	
	@Test
	public void testEqual() {
		assertTrue(exp1.equals(exp1));
		assertFalse(exp1.equals(exp2));
	}
}