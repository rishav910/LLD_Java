import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Task scheduler:
        // It should be able to add tasks (one-time)
        // Ability to cancel a task, delete a task, update a task

        // Follow up: Add priority to tasks (added comments)

        /*
            Entities:
            1. TaskSchedulerService - Singleton (User wise)
            2. Task - Time, Date, Name, id, Description
            3. TaskHandler - cancel/delete, update
            4. TaskStatus - RUNNING, NOT_STARTED, FINISHED
         */

        var service = new TaskSchedulerService();
        int t1 = service.addTask("Go to office", "Go to MathWorks office on Thursday");
        int t2 = service.addTask("Play games", "Play BattleGrounds Mobile India");
        int t3 = service.addTask("HLD", "Revise HLD concepts");
        int t4 = service.addTask("LLD", "Complete now");
        List<Integer> taskId = new ArrayList<>();
        taskId.add(t1); taskId.add(t2); taskId.add(t3); taskId.add(t4);

//        service.showTasks();

        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<3; i++) {
            final int idx = i;
            threads.add(new Thread(() -> {
                service.startTask(taskId.get(idx));
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000 + idx*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
            threads.get(i).start();
        }

        service.cancelTask("HLD", "Revise HLD concepts");

        for (int i=0; i<3; i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            service.stopTask(taskId.get(i));
            service.getTimeOfCompletion(taskId.get(i));
        }


        service.showTasks();
    }
}