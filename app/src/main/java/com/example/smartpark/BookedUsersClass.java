package com.example.smartpark;

public class BookedUsersClass {

    String name, contactNo, vehicleNo, slotNo;

    public BookedUsersClass(String name, String contactNo, String vehicleNo, String slotNo) {
        this.name = name;
        this.contactNo = contactNo;
        this.vehicleNo = vehicleNo;
        this.slotNo = slotNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }
}
