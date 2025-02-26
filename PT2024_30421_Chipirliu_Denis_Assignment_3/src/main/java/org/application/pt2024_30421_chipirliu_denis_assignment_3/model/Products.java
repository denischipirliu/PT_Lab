package org.application.pt2024_30421_chipirliu_denis_assignment_3.model;

/**
 * This class represents the Products table from the database
 */
public class Products {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    /**
     * This constructor creates a new product with the specified id, name, description, price and quantity
     *
     * @param id          the id of the product
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param quantity    the quantity of the product
     */
    public Products(Integer id, String name, String description, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * This constructor creates a new product with the specified name, description, price and quantity
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param quantity    the quantity of the product
     */
    public Products(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * This method returns the id of the product
     *
     * @return the id of the product
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns the name of the product
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the description of the product
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the price of the product
     *
     * @return the price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method returns the quantity of the product
     *
     * @return the quantity of the product
     */
    public Integer getQuantity() {
        return quantity;
    }
}
