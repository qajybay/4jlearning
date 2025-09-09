package berrx.multithreading;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakPrevent {


    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        for (int i = 0; i < 1_000_000; i++) {
            scheduler.scheduleTask("Task" + i);
        }
        scheduler.runAll();
    }

    public static class TaskScheduler {
        private final List<Runnable> tasks = new ArrayList<>();

        public void scheduleTask(String name) {
            String largeData = name.repeat(10_000); // имитируем большой объект,
            // should avoid adding this, can be in stack of a thead

            tasks.add(() -> {
                System.out.println("Running task: " + name);
            });
        }

        public void runAll() {
            tasks.forEach(Runnable::run);
        }
    }
}
