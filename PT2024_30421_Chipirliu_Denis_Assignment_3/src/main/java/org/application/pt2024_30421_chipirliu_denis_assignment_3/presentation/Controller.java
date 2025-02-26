package org.application.pt2024_30421_chipirliu_denis_assignment_3.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.ClientBLL;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.OrderBLL;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.ProductBLL;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.OrderDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.ProductDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Clients;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Orders;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Products;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.TableSelection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the Controller
 */
public class Controller {
    @FXML
    private Button clientsButton;
    @FXML
    private Button productsButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView tableView;
    private TableSelection tableSelection;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    /**
     * This constructor creates a new controller
     */
    public Controller() {
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
    }

    /**
     * This method initializes the controller
     */
    @FXML
    public void initialize() {
    }

    /**
     * This method handles the clients button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleClientsButton(ActionEvent actionEvent) {
        List<Object> objects = new ArrayList<>(clientBLL.findAll());
        retrieveProperties(objects);
        tableSelection = TableSelection.CLIENTS;
    }

    /**
     * This method handles the products button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleProductsButton(ActionEvent actionEvent) {
        ProductDAO productDAO = new ProductDAO();
        List<Object> objects = new ArrayList<>(productDAO.findAll());
        retrieveProperties(objects);
        tableSelection = TableSelection.PRODUCTS;
    }

    /**
     * This method handles the orders button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleOrdersButton(ActionEvent actionEvent) {
        OrderDAO orderDAO = new OrderDAO();
        List<Object> objects = new ArrayList<>(orderDAO.findAll());
        retrieveProperties(objects);
        tableSelection = TableSelection.ORDERS;
    }

    /**
     * This method handles the add button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleAddButton(ActionEvent actionEvent) {
        switch (tableSelection) {
            case CLIENTS -> {
                Dialog dialog = new Dialog();
                dialog.setTitle("Add client");
                Label nameLabel = new Label("Name:");
                TextField nameField = new TextField();
                Label addressLabel = new Label("Address:");
                TextField addressField = new TextField();
                Label emailLabel = new Label("Email:");
                TextField emailField = new TextField();
                Label phoneLabel = new Label("Phone:");
                TextField phoneField = new TextField();
                ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
                dialog.getDialogPane().setContent(new ScrollPane(new VBox(nameLabel, nameField, addressLabel, addressField, emailLabel, emailField, phoneLabel, phoneField)));
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButton) {
                        return new Clients(nameField.getText(), addressField.getText(), emailField.getText(), phoneField.getText());
                    }
                    return null;
                });
                Optional<Clients> result = dialog.showAndWait();
                try {
                    result.ifPresent(client -> clientBLL.addClient(client));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error adding client");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                tableView.getItems().clear();
                tableView.getColumns().clear();
                retrieveProperties(new ArrayList<>(clientBLL.findAll()));
            }
            case PRODUCTS -> {
                Dialog dialog = new Dialog();
                dialog.setTitle("Add product");
                Label nameLabel = new Label("Name:");
                TextField nameField = new TextField();
                Label descriptionLabel = new Label("Description:");
                TextField descriptionField = new TextField();
                Label priceLabel = new Label("Price:");
                TextField priceField = new TextField();
                Label quantityLabel = new Label("Quantity:");
                TextField quantityField = new TextField();
                ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
                dialog.getDialogPane().setContent(new ScrollPane(new VBox(nameLabel, nameField, descriptionLabel, descriptionField, priceLabel, priceField, quantityLabel, quantityField)));
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButton) {
                        return new Products(nameField.getText(), descriptionField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
                    }
                    return null;
                });
                Optional<Products> result = dialog.showAndWait();
                try {
                    result.ifPresent(product -> productBLL.addProduct(product));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error adding product");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                tableView.getItems().clear();
                tableView.getColumns().clear();
                retrieveProperties(new ArrayList<>(productBLL.findAll()));

            }
            case ORDERS -> {

                Dialog dialog = new Dialog();
                dialog.setTitle("Add order");
                Label clientLabel = new Label("Client:");
                ChoiceBox<String> clientChoiceBox = new ChoiceBox<>();
                clientChoiceBox.getItems().addAll(clientBLL.findAll().stream().map(Clients::getName).toList());
                Label productIdLabel = new Label("Product:");
                Label productLabel = new Label("Product:");
                ChoiceBox<String> productChoiceBox = new ChoiceBox<>();
                productChoiceBox.getItems().addAll(productBLL.findAll().stream().map(Products::getName).toList());
                Label quantityLabel = new Label("Quantity:");
                TextField quantityField = new TextField();
                ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
                dialog.getDialogPane().setContent(new ScrollPane(new VBox(clientLabel, clientChoiceBox, productLabel, productChoiceBox, quantityLabel, quantityField)));
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButton) {
                        return new Orders(clientBLL.findIdByName(clientChoiceBox.getValue()), productBLL.findIdByName(productChoiceBox.getValue()), Integer.parseInt(quantityField.getText()));
                    }
                    return null;
                });
                Optional<Orders> result = dialog.showAndWait();
                try {
                    result.ifPresent(order -> orderBLL.addOrder(order));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error adding order");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                tableView.getItems().clear();
                tableView.getColumns().clear();
                retrieveProperties(new ArrayList<>(orderBLL.findAll()));
            }
        }
    }

    /**
     * This method handles the edit button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleEditButton(ActionEvent actionEvent) {
        switch (tableSelection) {
            case CLIENTS -> {
                Clients selectedClient = (Clients) tableView.getSelectionModel().getSelectedItem();
                if (selectedClient != null) {
                    Dialog dialog = new Dialog();
                    dialog.setTitle("Edit client");
                    Label nameLabel = new Label("Name:");
                    TextField nameField = new TextField();
                    nameField.setText(selectedClient.getName());
                    Label addressLabel = new Label("Address:");
                    TextField addressField = new TextField();
                    addressField.setText(selectedClient.getAddress());
                    Label emailLabel = new Label("Email:");
                    TextField emailField = new TextField();
                    emailField.setText(selectedClient.getEmail());
                    Label phoneLabel = new Label("Phone:");
                    TextField phoneField = new TextField();
                    phoneField.setText(selectedClient.getPhone());
                    ButtonType editButton = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(editButton, ButtonType.CANCEL);
                    dialog.getDialogPane().setContent(new ScrollPane(new VBox(nameLabel, nameField, addressLabel, addressField, emailLabel, emailField, phoneLabel, phoneField)));
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == editButton) {
                            return new Clients(selectedClient.getId(), nameField.getText(), addressField.getText(), emailField.getText(), phoneField.getText());
                        }
                        return null;
                    });
                    Optional<Clients> result = dialog.showAndWait();
                    try {
                        result.ifPresent(client -> clientBLL.updateClient(client));
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error editing client");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    tableView.getItems().clear();
                    tableView.getColumns().clear();
                    retrieveProperties(new ArrayList<>(clientBLL.findAll()));
                }
            }
            case PRODUCTS -> {
                Products selectedProduct = (Products) tableView.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    Dialog dialog = new Dialog();
                    dialog.setTitle("Edit product");
                    Label nameLabel = new Label("Name:");
                    TextField nameField = new TextField();
                    nameField.setText(selectedProduct.getName());
                    Label descriptionLabel = new Label("Description:");
                    TextField descriptionField = new TextField();
                    descriptionField.setText(selectedProduct.getDescription());
                    Label priceLabel = new Label("Price:");
                    TextField priceField = new TextField();
                    priceField.setText(String.valueOf(selectedProduct.getPrice()));
                    Label quantityLabel = new Label("Quantity:");
                    TextField quantityField = new TextField();
                    quantityField.setText(String.valueOf(selectedProduct.getQuantity()));
                    ButtonType editButton = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(editButton, ButtonType.CANCEL);
                    dialog.getDialogPane().setContent(new ScrollPane(new VBox(nameLabel, nameField, descriptionLabel, descriptionField, priceLabel, priceField, quantityLabel, quantityField)));
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == editButton) {
                            return new Products(selectedProduct.getId(), nameField.getText(), descriptionField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
                        }
                        return null;
                    });
                    Optional<Products> result = dialog.showAndWait();
                    try {
                        result.ifPresent(product -> productBLL.updateProduct(product));
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error editing product");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    tableView.getItems().clear();
                    tableView.getColumns().clear();
                    retrieveProperties(new ArrayList<>(productBLL.findAll()));
                }
            }
        }
    }

    /**
     * This method handles the delete button
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleDeleteButton(ActionEvent actionEvent) {
        switch (tableSelection) {
            case CLIENTS -> {
                Clients selectedClient = (Clients) tableView.getSelectionModel().getSelectedItem();
                if (selectedClient != null) {
                    try {
                        clientBLL.deleteClient(selectedClient);
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error deleting client");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    tableView.getItems().clear();
                    tableView.getColumns().clear();
                    retrieveProperties(new ArrayList<>(clientBLL.findAll()));
                }
            }
            case PRODUCTS -> {
                Products selectedProduct = (Products) tableView.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    try {
                        productBLL.deleteProduct(selectedProduct);
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error deleting product");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    tableView.getItems().clear();
                    tableView.getColumns().clear();
                    retrieveProperties(new ArrayList<>(productBLL.findAll()));
                }
            }
        }
    }

    /**
     * This method retrieves the properties
     *
     * @param objects the objects
     */
    public void retrieveProperties(List<Object> objects) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        tableView.setEditable(true);
        Object object = objects.getFirst();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            TableColumn<Object, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tableView.getColumns().add(column);
        }
        for (Object obj : objects) {
            tableView.getItems().add(obj);
        }
        tableView.refresh();
    }

}