package org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Products;

/**
 * This class represents the validator for the product.
 */
public class ProductValidator {
    /**
     * This constructor creates a new product validator.
     */
    public ProductValidator() {
    }

    /**
     * This method validates the product.
     *
     * @param product The product to be validated.
     * @return boolean
     * @throws IllegalArgumentException If the product is null or if the name, quantity or price are null or negative.
     */
    public boolean validateProduct(Products product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("Product is null!");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty!");
        }
        if (product.getQuantity() == null || product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity is null or negative!");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price is null or negative!");
        }
        return true;
    }
}
