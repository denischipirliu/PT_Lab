package org.application.pt2024_30421_chipirliu_denis_assignment_3.model;

/**
 * This class represents the clients of the company
 */
public class Clients {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;

    /**
     * This constructor creates a new client with the specified id, name, address, email and phone
     *
     * @param id      the id of the client
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email of the client
     * @param phone   the phone of the client
     */
    public Clients(Integer id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * This constructor creates a new client with the specified name, address, email and phone
     *
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email of the client
     * @param phone   the phone of the client
     */
    public Clients(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * This method returns the id of the client
     *
     * @return the id of the client
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns the name of the client
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the address of the client
     *
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method returns the email of the client
     *
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method returns the phone of the client
     *
     * @return the phone of the client
     */
    public String getPhone() {
        return phone;
    }
}
