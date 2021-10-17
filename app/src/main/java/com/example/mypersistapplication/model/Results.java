package com.example.mypersistapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {
    public Name getName() {
        return name;
    }

    @SerializedName("name")
    @Expose
    private Name name;

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Picture getPicture() {
        return picture;
    }

    @SerializedName("picture")
    @Expose
    private Picture picture;

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Name getUserName() {
        return name;
    }


    public String getUserEmail() {
        return email;
    }


    public String getGender() {
        return gender;
    }


    public String getPhoneNumber() {
        return phone;
    }


    public Picture getUserProfile() {
        return picture;
    }

    @Override
    public String toString() {
        return "Results{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", picture=" + picture +
                '}';
    }

}
