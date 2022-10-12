package com.company.admindashboard;

public class MainModel {
    private String PetAge,PetBreed,PetName,ImageUrl,PetType,PetGender,PetAbout;
    MainModel(){

    }

    public MainModel(String petAge, String petBreed, String petName, String imageUrl, String petType, String petGender, String petAbout) {
        PetAge = petAge;
        PetBreed = petBreed;
        PetName = petName;
        ImageUrl = imageUrl;
        PetType = petType;
        PetGender = petGender;
        PetAbout = petAbout;
    }

    public String getPetAge() {
        return PetAge;
    }

    public void setPetAge(String petAge) {
        PetAge = petAge;
    }

    public String getPetBreed() {
        return PetBreed;
    }

    public void setPetBreed(String petBreed) {
        PetBreed = petBreed;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPetType() {
        return PetType;
    }

    public void setPetType(String petType) {
        PetType = petType;
    }

    public String getPetGender() {
        return PetGender;
    }

    public void setPetGender(String petGender) {
        PetGender = petGender;
    }

    public String getPetAbout() {
        return PetAbout;
    }

    public void setPetAbout(String petAbout) {
        PetAbout = petAbout;
    }
}
