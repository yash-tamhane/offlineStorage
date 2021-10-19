package com.example.mypersistapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypersistapplication.ClickListner;
import com.example.mypersistapplication.R;
import com.example.mypersistapplication.adapter.UserAdapter;
import com.example.mypersistapplication.databinding.ActivityMainBinding;
import com.example.mypersistapplication.model.Name;
import com.example.mypersistapplication.model.Results;
import com.example.mypersistapplication.model.RetrofitApiClient;
import com.example.mypersistapplication.model.UserList;
import com.example.mypersistapplication.model.database.UserDatabase;
import com.example.mypersistapplication.model.database.UserModel;
import com.example.mypersistapplication.viewModel.UserDataViewModel;
import com.example.mypersistapplication.viewModel.UserViewModelFactory;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private RecyclerView userRecycleView;
    private UserAdapter userAdapter;
    private ArrayList<UserList> listOfResults = new ArrayList<>();
    private ArrayList<UserModel> ofllineResults = new ArrayList<>();
    private UserDatabase userDatabase;
    private UserDataViewModel userDataViewModel;
    private TextView resultStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        userRecycleView = activityMainBinding.userList;
        userDatabase = UserDatabase.getInstance(getApplicationContext());
        setContentView(activityMainBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        userDataViewModel = ViewModelProviders.of(this).get(UserDataViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observeRequestListStatus();
        userAdapter = new UserAdapter(listOfResults,ofllineResults,getBaseContext());
        getUserList();
        userAdapter.setClickListner(new ClickListner() {
            @Override
            public void onPositionClicked(int position,String status) {
                userRecycleView.findViewHolderForAdapterPosition(position).itemView
                        .findViewById(R.id.request_status).setVisibility(View.VISIBLE);
                resultStatus = userRecycleView.findViewHolderForAdapterPosition(position).itemView
                        .findViewById(R.id.request_status);
                userRecycleView.findViewHolderForAdapterPosition(position).itemView
                        .findViewById(R.id.accept).setVisibility(View.GONE);
                userRecycleView.findViewHolderForAdapterPosition(position).itemView
                        .findViewById(R.id.decline).setVisibility(View.GONE);
                if(status.equalsIgnoreCase("accept")) {
                    resultStatus.setText("Request is accepted");
                    userDataViewModel.setRequestStatus(position,"Request is accepted");
                } else if (status.equalsIgnoreCase("decline")){
                    userDataViewModel.setRequestStatus(position,"Request is decline");
                    resultStatus.setText("Request is decline");
                }
            }
        });
    }

    private void getUserList() {
        Call<UserList> call = RetrofitApiClient.getInstance().getMyApi().getUserList();

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()) {
                    ofllineResults.clear();
                    for (int i = 0; i< response.body().getResults().length; i++) {
                        listOfResults.add(response.body());
                        userDataViewModel.addUserData( i, response);
                    }
                    userRecycleView.setAdapter(userAdapter);
                    userRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
            }


            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("userData",t.getCause().getLocalizedMessage());

                userDataViewModel.fetchUserData().observe(MainActivity.this, userData -> {
                    if(userData.size() != 0) {
                        for (int i = 0; i< userData.size(); i++) {
                            ofllineResults.add(userData.get(i));
                        }
                        userAdapter.notifyDataSetChanged();
                        userRecycleView.setAdapter(userAdapter);
                        userRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    } else {
                        userRecycleView.setVisibility(View.GONE);
                    }
                });

            }

        });
    }

    private void observeRequestListStatus () {
        userDataViewModel.fetchRequestStatusResult().observe(this,currentRequestState -> {
            userAdapter.dataOfCurrentRequestStatus(currentRequestState);
        });
    }
}
