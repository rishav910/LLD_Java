import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandler {
    // Improvement: Use Map<id, task> for linear scan from next time - Don't use List
    // Better to use ConcurrentHashMap<>() than Collections.SynchronizedHashMap(new HashMap<>());
    private final Map<Integer, Task> tasks = Collections.synchronizedMap(new HashMap<>());
    private final Map<Integer, Long> completeTime = new ConcurrentHashMap<>();
    private static int id;

    public int addTask (String title, String description) {
        // Once task is added -> sort according to priority using comparable interface
        // Collections.sort(tasks);
        id++;
        Task t = new Task(id, title, description);
        tasks.put(id, t);
        return id;
    }

    // Method overloading - different parameters
    public void updateTask (int taskId, String title, String description) {
        if (!tasks.containsKey(taskId)) {
            System.out.println("Task " + taskId + " does not exists");
            return;
        }
        Task t = tasks.get(taskId);
        t.setTitle(title);
        t.setDescription(description);
    }

    public void updateTask (int taskId, TaskStatus status) {
        if (!tasks.containsKey(taskId)) {
            System.out.println("Task " + taskId + " does not exists");
            return;
        }
        Task t = tasks.get(taskId);
        t.setTaskStatus(status);
    }

    public void cancelTask(int taskId) {
        if (!tasks.containsKey(taskId)) {
            System.out.println("Task " + taskId + " does not exists");
            return;
        }
        System.out.println("Task " + taskId + " is cancelled");
        Task t = tasks.get(taskId);
        t.setTaskStatus(TaskStatus.CANCELLED);
        tasks.remove(taskId);
    }

    public void showTasks () {
        for (Integer id: tasks.keySet()) {
            Task t = tasks.get(id);
            t.printTask();
        }
    }

    public void startTaskNow (int taskId) {
        if (!tasks.containsKey(taskId)) {
            System.out.println("Task " + taskId + " does not exists");
            return;
        }
        Task t = tasks.get(taskId);
        t.setTaskStatus(TaskStatus.RUNNING);
        System.out.println("Task " + taskId + " running");
    }

    public void stopTaskNow (int taskId) {
        if (!tasks.containsKey(taskId)) {
            System.out.println("Task " + taskId + " does not exists");
            return;
        }
        Task t = tasks.get(taskId);
        t.setTaskStatus(TaskStatus.FINISHED);
        t.setTimeOfCompletion(System.currentTimeMillis());
        completeTime.put(taskId, t.getTimeOfCompletion());
        tasks.remove(taskId);
        System.out.println("Task " + taskId + " stopped");

    }

    public long getTimeToComplete (int taskId) {
        return completeTime.getOrDefault(taskId, -1L);
    }
}
