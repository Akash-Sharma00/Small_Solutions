package com.example.smallsolutions;

public class RecyclerGetterNSetter {
    private int displayImg;
    private double starRating;
    private  String nameText;
    private String professionText;

    RecyclerGetterNSetter(int displayImg, double starRating, String nameText, String professionText)
    {
        this.displayImg = displayImg;
        this.starRating = starRating;
        this.nameText = nameText;
        this.professionText = professionText;
    }

    public int getDisplayImg() {
        return displayImg;
    }

    public double getStarRating() {
        return starRating;
    }

    public String getNameText() {
        return nameText;
    }

    public String getProfessionText() {
        return professionText;
    }

}
