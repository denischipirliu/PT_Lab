package main.logic;

import main.model.Server;
import main.model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int minQueue = Integer.MAX_VALUE;
        Server minServer = null;
        for(Server s : servers) {
            if(s.getTasks().length < minQueue) {
                minQueue = s.getTasks().length;
                minServer = s;
            }
        }
        minServer.addTask(t);
    }
}
