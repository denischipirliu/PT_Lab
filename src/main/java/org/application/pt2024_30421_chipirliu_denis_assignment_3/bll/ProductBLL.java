package org.application.pt2024_30421_chipirliu_denis_assignment_3.bll;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators.ProductValidator;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.ProductDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Products;

import java.util.List;

/**
 * This class represents the business logic layer for the product.
 */
public class ProductBLL {
    /**
     * This constructor creates a new product business logic layer.
     */
    public ProductBLL() {
    }

    /**
     * This method adds a product to the database.
     *
     * @param product The product to be added.
     * @throws IllegalArgumentException If the product is not valid.
     */
    public void addProduct(Products product) throws IllegalArgumentException {
        ProductDAO productDAO = new ProductDAO();
        ProductValidator productValidator = new ProductValidator();
        if (productValidator.validateProduct(product)) {
            productDAO.insert(product);
        }
    }

    /**
     * This method finds all products from the database.
     *
     * @return A list of all products.
     */
    public List<Products> findAll() {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findAll();
    }

    /**
     * This method updates a product from the database.
     *
     * @param product The product to be updated.
     */
    public void updateProduct(Products product) {
        ProductDAO productDAO = new ProductDAO();
        ProductValidator productValidator = new ProductValidator();
        if (productValidator.validateProduct(product)) {
            productDAO.update(product);
        }
    }

    /**
     * This method finds the id of a product by its name.
     *
     * @param value The name of the product.
     * @return The id of the product.
     */
    public Integer findIdByName(String value) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findIdByName(value);
    }

    /**
     * This method deletes a product from the database.
     *
     * @param selectedProduct The product to be deleted.
     */
    public void deleteProduct(Products selectedProduct) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(selectedProduct);
    }
}
