package com.example.smartpark;

public class AdminClass {

    String adminName, phone, location, id;

    public AdminClass(String adminName, String phone, String location, String id) {
        this.adminName = adminName;
        this.phone = phone;
        this.location = location;
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

