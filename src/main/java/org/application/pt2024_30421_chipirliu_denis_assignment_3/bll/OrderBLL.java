package org.application.pt2024_30421_chipirliu_denis_assignment_3.bll;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators.OrderValidator;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.ClientDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.OrderDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.ProductDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Orders;

import java.util.List;

/**
 * This class represents the business logic layer for the order.
 */
public class OrderBLL {
    /**
     * This constructor creates a new order business logic layer.
     */
    public OrderBLL() {
    }

    /**
     * This method adds an order to the database.
     *
     * @param order The order to be added.
     * @throws IllegalArgumentException If there is not enough stock.
     */
    public void addOrder(Orders order) throws IllegalArgumentException {
        OrderValidator orderValidator = new OrderValidator();
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        if (orderValidator.validateOrder(order, productDAO.findStockById(order.getProduct_id()))) {
            orderDAO.insert(order);
            productDAO.updateStock(order.getProduct_id(), productDAO.findStockById(order.getProduct_id()) - order.getQuantity());
        } else {
            throw new IllegalArgumentException("Not enough stock!");
        }
    }

    /**
     * This method finds all orders from the database.
     *
     * @return A list of all orders.
     */
    public List<Orders> findAll() {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll();
    }
}
