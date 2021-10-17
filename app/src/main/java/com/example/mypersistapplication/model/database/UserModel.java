package com.example.mypersistapplication.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mypersistapplication.model.Name;
import com.example.mypersistapplication.model.Picture;
import com.example.mypersistapplication.model.UserList;

@Entity
public class UserModel  {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    private Name name;

    private String email;

    private String gender;

    private String phone;

    private Picture picture;

    public UserModel(int user_id, Name name, String email, String gender, String phone, Picture picture, long timestamp, boolean syncPending) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.picture = picture;
        this.timestamp = timestamp;
        this.syncPending = syncPending;
    }

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "sync_pending")
    private boolean syncPending;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSyncPending() {
        return syncPending;
    }

    public void setSyncPending(boolean syncPending) {
        this.syncPending = syncPending;
    }
}
