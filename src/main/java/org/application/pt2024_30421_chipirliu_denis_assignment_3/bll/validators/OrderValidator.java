package org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Orders;

/**
 * This class represents the validator for the order.
 */
public class OrderValidator {
    /**
     * This constructor creates a new order validator.
     */
    public OrderValidator() {
    }

    /**
     * This method validates the order.
     *
     * @param order The order to be validated.
     * @param stockQuantity The quantity of the stock.
     * @return true
     * @throws IllegalArgumentException If the order is null or the stock quantity is not enough.
     */
    public boolean validateOrder(Orders order, Integer stockQuantity) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Client is null!");
        }
        if (order.getQuantity() > stockQuantity) {
            throw new IllegalArgumentException("Not enough stock!");
        }
        return true;
    }
}
