package com.example.smartpark;

public class SlotStatusClass {

    String slot, status;

    public SlotStatusClass(String slot, String status) {
        this.slot = slot;
        this.status = status;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
