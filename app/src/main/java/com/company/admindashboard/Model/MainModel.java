package com.company.admindashboard.Model;

public class MainModel {
    private String PetAge,PetBreed,PetName,ImageUrl,PetType,PetGender,PetAbout, City, State, Country, PetUser,PetAddress;
    MainModel(){

    }

    public MainModel(String petAge, String petBreed, String petName, String imageUrl, String petType, String petGender,String petUser, String petAbout, String petCity, String petState, String petCountry) {
        PetAge = petAge;
        PetBreed = petBreed;
        PetName = petName;
        ImageUrl = imageUrl;
        PetType = petType;
        PetGender = petGender;
        PetAbout = petAbout;
        City = petCity;
        State = petState;
        Country = petCountry;
        PetUser = petUser;
    }

    public String getPetAddress() {
        return PetAddress;
    }

    public void setPetAddress(String petAddress) {
        PetAddress = petAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPetUser() {
        return PetUser;
    }

    public void setPetUser(String petUser) {
        PetUser = petUser;
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

//    public String getPetAddress() {
//        return PetAddress;
//    }
//
//    public void setPetAddress(String petAddress) {
//        PetAddress = petAddress;
//    }
}
