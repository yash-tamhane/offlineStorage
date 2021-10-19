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

    @Query("SELECT * FROM usermodel WHERE user_id = :user_id ")
    List<Results> setSyncStatus(long user_id);

    @Query("UPDATE usermodel SET sync_pending =:isSync WHERE user_id = :id")
    void updateSyncStatus(int id,String isSync );

    @Query("SELECT sync_pending FROM usermodel ")
    List<String> getSyncStatus();
}
