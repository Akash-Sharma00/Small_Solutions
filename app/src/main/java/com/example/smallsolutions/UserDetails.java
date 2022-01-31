package com.example.smallsolutions;
import java.io.Serializable;

class UserDetails implements Serializable {

    private String userName, userEmail,  userPhoneNo, age, experience, profession, imageURL, time;

    public UserDetails() {
//        Empty constructor needed
    }

    public UserDetails(String userName, String userPhoneNo, String profession, String time, String imageURL){
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.profession = profession;
        this.time = time;
        this.imageURL = imageURL;
    }

    public UserDetails(String userName, String userEmail, String userPhoneNo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
    }

    public UserDetails(String userName, String userEmail, String userPhoneNo, String age, String experience, String profession) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.age = age;
        this.experience = experience;
        this.profession = profession;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getAge() {
        return age;
    }

    public String getExperience() {
        return experience;
    }

    public String getProfession() {
        return profession;
    }

    public String getImageURL(){
        return imageURL;
    }
    
    public String getTime() {
        return time;
    }
}
