package main.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    int maxTasksPerServer;
    private AtomicInteger totalWaitingTime = new AtomicInteger(0);
    private AtomicInteger totalServiceTime = new AtomicInteger(0);
    private AtomicInteger totalTasks = new AtomicInteger(0);
    private Thread thread;

    public Server(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
        tasks = new ArrayBlockingQueue<>(maxTasksPerServer);
        waitingPeriod = new AtomicInteger(0);
    }

    public synchronized void addTask(Task task) {
        tasks.add(task);
        waitingPeriod.addAndGet(task.getServiceTime());
        totalWaitingTime.addAndGet(waitingPeriod.get());
        totalServiceTime.addAndGet(task.getServiceTime());
        totalTasks.incrementAndGet();
    }

    public void run() {
        while (true) {
            try {
                Task task = tasks.peek();
                if (task != null) {
                    Thread.sleep(1000);
                    task.setServiceTime(task.getServiceTime() - 1);
                    waitingPeriod.addAndGet(-1);
                    if (task.getServiceTime() == 0) {
                        tasks.take();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public double getAverageWaitingTime() {
        return totalTasks.get() > 0 ? (double) totalWaitingTime.get() / totalTasks.get() : 0;
    }

    public double getAverageServiceTime() {
        return totalTasks.get() > 0 ? (double) totalServiceTime.get() / totalTasks.get() : 0;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
