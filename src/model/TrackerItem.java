package model;
import java.util.Objects;
import java.util.UUID;


/*
 * A tracker item with attributes id, title and description
 * 
 * @require title not null
 * @require description not null
 * 
 * @ensure getId() == UUID id
 * @ensure getTitle() == String title
 * @ensure getDescription() == String description
 */
public abstract class TrackerItem {
    private final UUID id;
    private String title;
    private String description;

    protected TrackerItem(String title, String description) {
        this.id = UUID.randomUUID();

        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
    }
    
    /*
     * set new title
     * @param title not null
     */
    protected void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title can't be null!");
    }
    
    /*
     * set new description
     * @param description not null
     */
    protected void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "Title can't be null!");
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}