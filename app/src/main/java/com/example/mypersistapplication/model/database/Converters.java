package com.example.mypersistapplication.model.database;

import androidx.room.TypeConverter;

import com.example.mypersistapplication.model.Name;
import com.example.mypersistapplication.model.Picture;
import com.example.mypersistapplication.model.Results;
import com.example.mypersistapplication.model.UserList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class Converters {

    @TypeConverter
    public Name getStringToName (String name) {
         return new Name();
    }

    @TypeConverter
    public String getNames(Name name) {
        return name.toString();
    }



    @TypeConverter
    public String getPicture(Picture picture) {
        return picture.toString();
    }

    @TypeConverter
    public Picture getStringToPicture(String picture) {
        return new Picture();
    }

    @TypeConverter
    public String getUserList(Results userList) {
        return userList.toString();
    }

    @TypeConverter
    public Results getStringToUserList(String userList) {
        return new Gson().fromJson(userList, Results.class);
    }
}
