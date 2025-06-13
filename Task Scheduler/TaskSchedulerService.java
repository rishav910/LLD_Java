public class TaskSchedulerService {
    private TaskHandler handler;

    public TaskSchedulerService () {
        handler = new TaskHandler();
    }

    // Add task to task scheduler
    public int addTask (String title, String description) {
        return handler.addTask(title, description);
    }

    // Cancel a task
    public void cancelTask (int taskId) {
        handler.cancelTask(taskId);
    }

    // Update a task
    // 1. Update task title, description
    public void updateTask (int taskId, String title, String description) {
        handler.updateTask(taskId, title, description);
    }

    // 2. Update task status
    public void updateTask (int taskId, TaskStatus status) {
        handler.updateTask(taskId, status);
    }

    public void showTasks () {
        System.out.println("\nExisting tasks:");
        handler.showTasks();
    }

    public void startTask (int taskId) {
        handler.startTaskNow(taskId);
    }

    public void stopTask (int taskId) {
        handler.stopTaskNow(taskId);
    }

    public void getTimeOfCompletion (int taskId) {
        long timeMS = handler.getTimeToComplete(taskId);
        if(timeMS == -1) {
            System.out.println("Task does not exist");
            return;
        }
        double timeSeconds = (double)timeMS/1000;
        System.out.println("Time to complete Task " + taskId + " is: " + timeSeconds);
    }
}
