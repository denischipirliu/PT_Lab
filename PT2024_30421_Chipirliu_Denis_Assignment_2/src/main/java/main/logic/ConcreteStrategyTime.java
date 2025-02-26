package main.logic;

import main.model.Server;
import main.model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int minTime = Integer.MAX_VALUE;
        Server minServer = null;
        for(Server s : servers) {
            if(s.getWaitingPeriod() < minTime) {
                minTime = s.getWaitingPeriod();
                minServer = s;
            }
        }
        minServer.addTask(t);
    }
}
