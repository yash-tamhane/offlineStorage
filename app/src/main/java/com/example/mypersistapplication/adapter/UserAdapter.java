package com.example.mypersistapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypersistapplication.ClickListner;
import com.example.mypersistapplication.R;
import com.example.mypersistapplication.model.Results;
import com.example.mypersistapplication.model.UserList;
import com.example.mypersistapplication.model.database.UserModel;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<UserList> resultsList;
    private ArrayList<UserModel> ofllineResults;
    private Context context;
    private int position = -1 ;
    private ClickListner clickListner;

    public UserAdapter(ArrayList<UserList> resultsList,ArrayList<UserModel> ofllineResults, Context context) {
        this.resultsList = resultsList;
        this.ofllineResults = ofllineResults;
        this.context = context;
    }

    public void setClickListner (ClickListner clickListner) {
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(resultsList != null) {
            UserList userList = resultsList.get(position);
            Results results = userList.getResults()[position];
            Picasso.get().load(results.getUserProfile().getThumbnail()).placeholder(R.drawable.user).into(holder.image);
            holder.userName.setText(results.getName().getTitle() + " " + results.getUserName().getFirst() + " " + results.getUserName().getLast());
            holder.userEmail.setText(results.getEmail());
            holder.userContact.setText(results.getPhone());
            holder.userGender.setText(results.getGender());
        } else {
            UserModel userModel = ofllineResults.get(position);
            Picasso.get().load(userModel.getPicture().getThumbnail()).placeholder(R.drawable.user).into(holder.image);
            holder.userName.setText(userModel.getName().getTitle() + " " + userModel.getName().getFirst() + " " + userModel.getName().getLast());
            holder.userEmail.setText(userModel.getEmail());
            holder.userContact.setText(userModel.getPhone());
            holder.userGender.setText(userModel.getGender());
        }

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView userName, userEmail, userContact, userGender, requestStatus;
        Button acceptButton, declineButton;
        private WeakReference<ClickListner> listenerRef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listenerRef = new WeakReference<>(clickListner);
            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            userContact = itemView.findViewById(R.id.user_contact);
            userGender = itemView.findViewById(R.id.user_gender);
            acceptButton = itemView.findViewById(R.id.accept);
            declineButton = itemView.findViewById(R.id.decline);
            requestStatus = itemView.findViewById(R.id.request_status);
            acceptButton.setOnClickListener(this);
            declineButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == acceptButton.getId()) {
                listenerRef.get().onPositionClicked(getAdapterPosition(),"accept");
            } else if (view.getId() == declineButton.getId()) {
                listenerRef.get().onPositionClicked(getAdapterPosition(),"decline");
            }

        }
    }


}
