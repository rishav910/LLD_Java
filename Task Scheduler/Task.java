import java.util.Date;
import java.util.Objects;

public class Task {
    // Follow up: Priority -> add a field 'priority'
    // implements Comparable -> compareTo (with higher priority set first)
    private String description;
    private String title;
    private final int id;
    private long timeOfCompletion;
    private TaskStatus taskStatus;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timeOfCompletion = System.currentTimeMillis();
        this.taskStatus = TaskStatus.NOT_STARTED;
    }

    public void printTask () {
        System.out.println("Task " + id + ": ");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Task Status: " + taskStatus.name() + "\n");
    }

    // Override equals() and hashCode() of Object class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof Task)) return false;

        Task t = (Task)o;
        return description.equals(t.getDescription()) && title.equals(t.getTitle());
    }

    @Override
    public int hashCode () {
        return Objects.hash(description, title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public long getTimeOfCompletion() {
        return timeOfCompletion;
    }

    public void setTimeOfCompletion(long currentTime) {
        this.timeOfCompletion = currentTime - this.timeOfCompletion;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
