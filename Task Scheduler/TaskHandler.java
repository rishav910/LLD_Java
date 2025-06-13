import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandler {
    private final List<Task> tasks = Collections.synchronizedList(new ArrayList<>());
    private final Map<Integer, Long> completeTime = new ConcurrentHashMap<>();
    private static int id;

    public int addTask (String title, String description) {
        // Once task is added -> sort according to priority using comparable interface
        // Collections.sort(tasks);
        id++;
        Task t = new Task(id, title, description);
        tasks.add(t);
        return id;
    }

    // Method overloading - different parameters
    public void updateTask (Task task, String title, String description) {
        for (Task taskp : tasks) {
            if(taskp.equals(task)) {
                taskp.setTitle(title);
                taskp.setDescription(description);
            }
        }
    }

    public void updateTask (Task task, TaskStatus status) {
        task.setTaskStatus(status);
    }

    public void cancelTask(Task task) {
        if (tasks.indexOf(task) == -1) {
            System.out.println("Task with title: " + task.getTitle() + " does not exists");
            return;
        }
        System.out.println("Task with title: " + task.getTitle() + " is cancelled");
        task.setTaskStatus(TaskStatus.CANCELLED);
        tasks.remove(task);
    }

    public void showTasks () {
        for (Task t:tasks) {
            t.printTask();
        }
    }

    public void startTaskNow (int taskId) {
        for (Task t : tasks) {
            if(t.getId() == taskId) {
                t.setTaskStatus(TaskStatus.RUNNING);
                System.out.println("Task " + taskId + " running");
                return;
            }
        }
        System.out.println("Task " + taskId + " does not exist");
    }

    public void stopTaskNow (int taskId) {
        for (Task t : tasks) {
            if(t.getId() == taskId) {
                t.setTaskStatus(TaskStatus.FINISHED);
                t.setTimeOfCompletion(System.currentTimeMillis());
                completeTime.put(taskId, t.getTimeOfCompletion());
                tasks.remove(t);
                System.out.println("Task " + taskId + " stopped");
                return;
            }
        }
        System.out.println("Task " + taskId + " does not exist");

    }

    public long getTimeToComplete (int taskId) {
        return completeTime.getOrDefault(taskId, -1L);
    }
}
