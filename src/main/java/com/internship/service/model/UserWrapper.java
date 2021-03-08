package com.internship.service.model;

public class UserWrapper {

    Long id;
    String status;
    String firstName;

    public UserWrapper(Long id,
                       String status,
                       String firstName) {
        this.id = id;
        this.status = status;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
