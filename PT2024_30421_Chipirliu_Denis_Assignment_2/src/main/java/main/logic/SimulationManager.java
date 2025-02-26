package main.logic;

import javafx.application.Platform;
import main.model.SelectionPolicy;
import main.model.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import main.model.Server;
import main.controller.Controller;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfTasks;
    public SelectionPolicy selectionPolicy;
    public Scheduler scheduler;
    private List<Task> generatedTasks;
    private int peakHour;
    private int peakLoad;
    private final Controller controller;

    public SimulationManager(Controller controller, int numberOfServers, int numberOfTasks, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, int timeLimit, SelectionPolicy selectionPolicy) {
        this.controller = controller;
        this.numberOfServers = numberOfServers;
        this.numberOfTasks = numberOfTasks;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.timeLimit = timeLimit;
        this.selectionPolicy = selectionPolicy;
        clearLog();
        scheduler = new Scheduler(numberOfServers, 100);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomTasks();
    }
    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();
        for (int i = 1; i <= numberOfTasks; i++) {
            Random random = new Random();
            int processingTime = random.nextInt(minProcessingTime, maxProcessingTime);
            int arrivalTime = random.nextInt(minArrivalTime, maxArrivalTime);
            Task t = new Task(i, arrivalTime, processingTime);
            generatedTasks.add(t);
        }
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
    }

    private String getLog(int currentTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("Time " + currentTime + "\n");
        sb.append("Waiting clients: ");
        if (generatedTasks.isEmpty()) {
            sb.append("\n");
        } else {
            for (Task t : generatedTasks) {
                sb.append("(" + t.getId() + "," + t.getArrivalTime() + "," + t.getServiceTime() + "); ");
            }
            sb.append("\n");
        }
        for (int i = 1; i <= numberOfServers; i++) {
            if (scheduler.getServers().get(i - 1).getTasks().length == 0) {
                sb.append("Queue " + i + ": closed\n");
            } else {
                sb.append("Queue " + i + ": ");
                for (Task t : scheduler.getServers().get(i - 1).getTasks()) {
                    sb.append("(" + t.getId() + "," + t.getArrivalTime() + "," + t.getServiceTime() + "); ");
                }
                sb.append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private void saveToTextFile(String text) {
        File file = new File("log.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(text);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public int getPeakHour() {
        return peakHour;
    }
    private String simulationResults() {
        double averageWaitingTime = 0;
        double averageServiceTime = 0;
        for (int i = 1; i <= numberOfServers; i++) {
            averageWaitingTime += scheduler.getServers().get(i - 1).getAverageWaitingTime();
            averageServiceTime += scheduler.getServers().get(i - 1).getAverageServiceTime();
        }
        averageWaitingTime /= numberOfServers;
        averageServiceTime /= numberOfServers;
        StringBuilder sb = new StringBuilder();
        sb.append("Average waiting time: " + averageWaitingTime + "\n");
        sb.append("Average service time: " + averageServiceTime + "\n");
        sb.append("Peak hour: " + getPeakHour() + "\n");
        return sb.toString();
    }
    private static void clearLog() {
        File file = new File("log.txt");
        if (file.exists()) {
            file.delete();
        }
    }
    private boolean allTasksCompleted() {
        return scheduler.getServers().stream().allMatch(server -> server.getTasks().length == 0);
    }
    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            Iterator<Task> iterator = generatedTasks.iterator();
            while (iterator.hasNext()) {
                Task t = iterator.next();
                if (t.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(t);
                    iterator.remove();
                }
            }
            int currentLoad = scheduler.getServers().stream().mapToInt(Server::getWaitingPeriod).sum();
            if (currentLoad > peakLoad) {
                peakLoad = currentLoad;
                peakHour = currentTime;
            }
            String log = getLog(currentTime);
            String finalLog = log;
            Platform.runLater(() -> controller.logArea.appendText(finalLog));
            saveToTextFile(log);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(allTasksCompleted() && generatedTasks.isEmpty()) {
                log = getLog(currentTime);
                String finalLog1 = log;
                Platform.runLater(() -> controller.logArea.appendText(finalLog1));
                saveToTextFile(log);
                break;
            }
        }
        controller.resultArea.setText(simulationResults());
        saveToTextFile(simulationResults());
    }

}
