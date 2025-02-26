package org.application.pt2024_30421_chipirliu_denis_assignment_3.model;

/**
 * This class represents the Orders table from the database
 */
public class Orders {
    private Integer id;
    private Integer client_id;
    private Integer product_id;
    private Integer quantity;
    private Double total_price;

    /**
     * This constructor creates a new order with the specified id, client_id, product_id, quantity and total_price
     *
     * @param id          the id of the order
     * @param client_id   the id of the client
     * @param product_id  the id of the product
     * @param quantity    the quantity of the product
     * @param total_price the total price of the order
     */
    public Orders(Integer id, Integer client_id, Integer product_id, Integer quantity, Double total_price) {
        this.id = id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    /**
     * This constructor creates a new order with the specified client_id, product_id and quantity
     *
     * @param client_id  the id of the client
     * @param product_id the id of the product
     * @param quantity   the quantity of the product
     */
    public Orders(Integer client_id, Integer product_id, Integer quantity) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * This method returns the id of the order
     *
     * @return the id of the order
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns the id of the client
     *
     * @return the id of the client
     */
    public Integer getClient_id() {
        return client_id;
    }

    /**
     * This method returns the id of the product
     *
     * @return the id of the product
     */
    public Integer getProduct_id() {
        return product_id;
    }

    /**
     * This method returns the quantity of the product
     *
     * @return the quantity of the product
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method returns the total price of the order
     *
     * @return the total price of the order
     */
    public Double getTotal_price() {
        return total_price;
    }

}
