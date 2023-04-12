package com.example.smartpark;

public class UserClass {              // to store data of our users

    String userName, phone, vehicle, password;

    public UserClass() {              // empty constructor to avoid any error in firebase
    }

    public UserClass(String userName, String phone, String vehicle, String password) {
        this.userName = userName;
        this.phone = phone;
        this.vehicle = vehicle;
        this.password = password;
    }

    public String getUserName() {return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
