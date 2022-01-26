package com.example.smallsolutions;

public class callLogRecycler {
    private int receiver_displayImg;
    private String receiver_nameText;
    private String receiver_professionText;
    private String receiver_contact;

    public callLogRecycler(int receiver_displayImg, String receiver_nameText, String receiver_professionText, String receiver_contact) {
        this.receiver_displayImg = receiver_displayImg;
        this.receiver_nameText = receiver_nameText;
        this.receiver_professionText = receiver_professionText;
        this.receiver_contact = receiver_contact;
    }

    public int getReceiver_displayImg() {
        return receiver_displayImg;
    }

    public String getReceiver_nameText() {
        return receiver_nameText;
    }

    public String getReceiver_professionText() {
        return receiver_professionText;
    }

    public String getReceiver_contact() {
        return receiver_contact;
    }
}
