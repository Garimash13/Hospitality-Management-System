package com.project;

public class Guest {
    private int guestId;
    private String name;
    private String email;
    private String phoneNumber;

    public Guest(int guestId, String name, String email, String phoneNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}

