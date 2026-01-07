package ru.blurry16.autowb.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TickScheduler {

    private static final List<RunnableTask> tasks = new ArrayList<>();

    public static void schedule(int delayTicks, Runnable task) {
        tasks.add(new RunnableTask(delayTicks, task));
    }

    public static void tick() {

        Iterator<RunnableTask> it = tasks.iterator();
        while (it.hasNext()) {
            RunnableTask t = it.next();
            t.ticks--;
            if (t.ticks <= 0) {
                t.task.run();
                it.remove();
            }
        }
    }


    private static class RunnableTask {
        int ticks;
        Runnable task;

        RunnableTask(int ticks, Runnable task) {
            this.ticks = ticks;
            this.task = task;
        }
    }
}
