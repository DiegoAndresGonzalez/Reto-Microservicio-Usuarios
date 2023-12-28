package com.pragma.powerup.domain.model;

public class RoleModel {
    private Long id;
    private String role;
    private String description;

    public RoleModel(Long id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}