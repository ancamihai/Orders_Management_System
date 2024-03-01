package org.example.model;

public class Client {

    private int id;
    private String name;
    private String email;
    private String address;

    public Client() {
    }

    public Client(String name) {
        super();
        this.name = name;
    }

    public Client(int id, String name, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Client(String name, String email, String address) {
        super();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
