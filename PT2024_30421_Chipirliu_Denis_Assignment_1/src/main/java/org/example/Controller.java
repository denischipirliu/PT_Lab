package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.models.Polynomial;


public class Controller {
    @FXML
    private TextField polynomial1;
    @FXML
    private TextField polynomial2;
    @FXML
    private Button add;
    @FXML
    private Button subtract;
    @FXML
    private Button multiply;
    @FXML
    private Button divide;
    @FXML
    private Button derivative;
    @FXML
    private Button integrate;
    @FXML
    private TextField result;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized");
        add.setOnAction(this::addButton);
        subtract.setOnAction(this::subtractButton);
        multiply.setOnAction(this::multiplyButton);
        divide.setOnAction(this::divideButton);
        derivative.setOnAction(this::derivativeButton);
        integrate.setOnAction(this::integrateButton);
    }

    @FXML
    private void addButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial p2 = new Polynomial(polynomial2.getText());
            Polynomial result = p1.addPolynomial(p2);
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
            e.printStackTrace();
        }
    }

    @FXML
    private void subtractButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial p2 = new Polynomial(polynomial2.getText());
            Polynomial result = p1.subtractPolynomial(p2);
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
            e.printStackTrace();
        }
    }

    @FXML
    private void multiplyButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial p2 = new Polynomial(polynomial2.getText());
            Polynomial result = p1.multiplyPolynomial(p2);
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
            e.printStackTrace();
        }
    }

    @FXML
    private void divideButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial p2 = new Polynomial(polynomial2.getText());
            Polynomial result = p1.dividePolynomial(p2);
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
        }
    }

    @FXML
    private void derivativeButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial result = p1.derivativePolynomial();
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
            e.printStackTrace();
        }
    }

    @FXML
    private void integrateButton(ActionEvent event) {
        try {
            Polynomial p1 = new Polynomial(polynomial1.getText());
            Polynomial result = p1.integratePolynomial();
            this.result.setText(result.toString());
        } catch (Exception e) {
            result.setText("Invalid input");
            e.printStackTrace();
        }
    }
}