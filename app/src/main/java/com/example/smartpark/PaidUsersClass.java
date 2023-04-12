package com.example.smartpark;

public class PaidUsersClass {

    String userName, userContact;
    boolean paymentStatus;

    public PaidUsersClass(String userName, String userContact, boolean paymentStatus) {
        this.userName = userName;
        this.userContact = userContact;
        this.paymentStatus = paymentStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
