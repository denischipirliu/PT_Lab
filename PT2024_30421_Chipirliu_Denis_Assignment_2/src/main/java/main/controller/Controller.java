package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.logic.SimulationManager;
import main.model.SelectionPolicy;


public class Controller {
    @FXML
    TextField queueField;
    @FXML
    TextField clientsField;
    @FXML
    TextField simulationTimeField;
    @FXML
    TextField minArrivalTimeField;
    @FXML
    TextField maxArrivalTimeField;
    @FXML
    TextField minServiceTimeField;
    @FXML
    TextField maxServiceTimeField;
    @FXML
    public TextArea logArea;
    @FXML
    public TextArea resultArea;
    @FXML
    Button runButton;
    @FXML
    Button clearButton;
    @FXML
    ChoiceBox<String> selectionPolicyBox;
    private Thread simulationThread;
    private SimulationManager simulationManager;

    @FXML
    private void initialize() {
        selectionPolicyBox.getItems().add("SHORTEST_TIME");
        selectionPolicyBox.getItems().add("SHORTEST_QUEUE");
        selectionPolicyBox.setValue("SHORTEST_TIME");
        logArea.setEditable(false);
        resultArea.setEditable(false);
    }

    @FXML
    public void run(ActionEvent event) {
        int numberOfQueues = Integer.parseInt(queueField.getText());
        int numberOfClients = Integer.parseInt(clientsField.getText());
        int simulationTime = Integer.parseInt(simulationTimeField.getText());
        int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
        int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());
        int minServiceTime = Integer.parseInt(minServiceTimeField.getText());
        int maxServiceTime = Integer.parseInt(maxServiceTimeField.getText());
        SelectionPolicy selectionPolicy = SelectionPolicy.valueOf(selectionPolicyBox.getValue());
        simulationManager = new SimulationManager(this, numberOfQueues, numberOfClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, simulationTime, selectionPolicy);
        simulationThread = new Thread(simulationManager);
        simulationThread.start();
    }

    @FXML
    public void clear(ActionEvent event) {
        logArea.clear();
        resultArea.clear();
        queueField.clear();
        clientsField.clear();
        simulationTimeField.clear();
        minArrivalTimeField.clear();
        maxArrivalTimeField.clear();
        minServiceTimeField.clear();
        maxServiceTimeField.clear();
    }
}
