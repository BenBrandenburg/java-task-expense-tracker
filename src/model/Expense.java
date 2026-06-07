package model;
import java.time.LocalDate;
import java.util.Objects;

public class Expense extends TrackerItem{
	private ExpenseCategory category;
	private double value;
	private LocalDate date;
	
    /**
     * @require title != null
     * @require description != null
     * @require value > 0
     *
     * @ensure getId() == id
     * @ensure getTitle() == title
     * @ensure getDescription() == description
     * @ensure getDate() == date
     * @ensure getCategory() == category
     * @ensure getValue() == value
     */
	public Expense(String title, String description, ExpenseCategory category, double value) {
		super(title, description);
		
		if (value < 0.0) {
			throw new IllegalArgumentException("Value must be greater than 0!");
		}
		this.category = Objects.requireNonNull(category, "Category can't be null!");
		this.value = value;
		this.date = LocalDate.now();
	}
	
	/*
	 *  change title of expense
	 *  @require newTitle not null
	 */
	public void changeTitle(String newTitle) {
		setTitle(newTitle);
	}
	
	/*
	 * change description of expense
	 * @require newDescription not null
	 */
	public void changeDescription(String newDescription) {
		setDescription(newDescription);
	}
	
	/*
	 * change status of task
	 */
	public void changeValue(double newValue) {
		if (newValue < 0.0) {
			throw new IllegalArgumentException("New value must be greater than 0!");
		}
		value = newValue;		
	}
	
	@Override
	public boolean equals(Object other) {
		return getTitle().equals(((TrackerItem) other).getTitle())	// same title
			&& getDescription().equals(((TrackerItem) other).getDescription()) // same description
			&& getDate().equals(((Expense) other).getDate())
			&& getCategory().equals(((Expense) other).getCategory())
			&& getValue() == ((Expense) other).getValue(); // same creation date
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(
	    	getTitle(),
	    	getDescription(),
	    	getDate(),
	    	getCategory(),
	    	getValue()
	    );
	}
	
	
	public LocalDate getDate() {
		return date;
	}
	
	public ExpenseCategory getCategory() {
		return category;
	}
	
	public double getValue() {
		return value;
	}
	
}
