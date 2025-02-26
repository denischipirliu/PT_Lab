package main.logic;

import main.model.SelectionPolicy;
import main.model.Server;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server(maxTasksPerServer));
            Thread thread = new Thread(servers.get(i));
            thread.start();
            servers.get(i).setThread(thread);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        } else {
            strategy = new ConcreteStrategyTime();
        }
    }

    public synchronized void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }
}
