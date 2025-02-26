package main.logic;

import main.model.Server;
import main.model.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}
