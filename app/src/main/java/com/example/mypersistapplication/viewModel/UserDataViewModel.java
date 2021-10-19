package com.example.mypersistapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.mypersistapplication.model.UserList;
import com.example.mypersistapplication.model.database.UserDatabase;
import com.example.mypersistapplication.model.database.UserModel;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class UserDataViewModel extends AndroidViewModel {

    private UserDatabase userDatabase;
    private UserModel userModel;

    private MutableLiveData<List<UserModel>> dataOfUsers = new MutableLiveData<>();
    private MutableLiveData<List<String>> requestState = new MutableLiveData<>();

    public UserDataViewModel(@NonNull Application application) {
        super(application);
        userDatabase = UserDatabase.getInstance(application);
    }

    public void addUserData(int i, Response<UserList> response) {
        Executors.newSingleThreadExecutor().execute(() -> {
            // todo: background tasks
            userModel = new UserModel(i,response.body().getResults()[i].getUserName()
                    ,response.body().getResults()[i].getUserEmail()
                    ,response.body().getResults()[i].getGender()
                    ,response.body().getResults()[i].getPhoneNumber()
                    ,response.body().getResults()[i].getUserProfile()
                    ,System.currentTimeMillis()
                    ,null);
            userDatabase.userDao().add(userModel);

        });
    }

    public void setRequestStatus(int i,String status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDatabase.userDao().updateSyncStatus(i,status);

        });

    }

    public MutableLiveData<List<String>> fetchRequestStatusResult() {
        Executors.newSingleThreadExecutor().execute(() -> {
            requestState.postValue(userDatabase.userDao().getSyncStatus());
        });
        return requestState;
    }


    public MutableLiveData<List<UserModel>> fetchUserData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            dataOfUsers.postValue(userDatabase.userDao().getCatcheData());
        });
        return dataOfUsers;
    }
}
