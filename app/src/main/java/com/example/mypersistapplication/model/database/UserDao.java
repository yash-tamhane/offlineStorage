package com.example.mypersistapplication.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.mypersistapplication.model.Results;
import java.util.List;


@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(UserModel addUsers);

    @Query("SELECT * FROM usermodel")
    List<UserModel> getCatcheData();

    @Query("SELECT * FROM usermodel WHERE user_id = :user_id ORDER BY timestamp DESC")
    List<Results> getSyncStatus(long user_id);
}
