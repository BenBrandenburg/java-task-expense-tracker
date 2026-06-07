package model;
import java.time.LocalDate;
import java.util.Objects;

public class Task extends TrackerItem {
	private final LocalDate createdDate;
	private LocalDate dueDate;
	private TaskStatus status;
	
    /**
     * @require title != null
     * @require description != null
     * @require dueDate future date
     *
     * @ensure getId() == id
     * @ensure getTitle() == title
     * @ensure getDescription() == description
     * @ensure getCreatedDate() == createDate
     * @ensure getDueDate() == dueDate
     * @ensure getStatus() == status
     */
	public Task(String title, String description, LocalDate dueDate, TaskStatus status) {
	    super(title, description);

	    this.createdDate = LocalDate.now();
	    this.dueDate = Objects.requireNonNull(dueDate, "Due date can't be null!");	
	    this.status = Objects.requireNonNull(status, "Status can't be null!");
	}
	
	/*
	 *  change title of task
	 *  @require newTitle not null
	 */
	public void changeTitle(String newTitle) {
		setTitle(newTitle);
	}
	
	/*
	 * change description of task
	 * @require newDescription not null
	 */
	public void changeDescription(String newDescription) {
		setDescription(newDescription);
	}
	
	/*
	 * change due date of task
	 */
	public void changeDueDate(LocalDate newDate) {
		this.dueDate = Objects.requireNonNull(newDate, "New date can't be null!");
	}
	
	/*
	 * change status of task
	 */
	public void changeStatus(TaskStatus newStatus) {
		this.status = Objects.requireNonNull(newStatus, "New status can't be null!");		
	}
	
	@Override
	public boolean equals(Object other) {
		return getTitle().equals(((TrackerItem) other).getTitle())	// same title
			&& getDescription().equals(((TrackerItem) other).getDescription()) // same description
			&& getCreatedDate().equals(((Task) other).getCreatedDate()) // same creation date
			&& getDueDate().equals(((Task) other).getDueDate()); // same due date
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(
	    	getTitle(),
	    	getDescription(),
	    	getCreatedDate(),
	    	getDueDate()
	    );
	}
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
}
