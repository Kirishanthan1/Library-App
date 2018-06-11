package hnditcom.libraryapp.activities.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.model.User;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.ViewHolder>{
ArrayList<User> userArrayList;

    public  RecyclerAdapterUser(ArrayList<User> userArrayList){
    this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_user,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.tvUsername.setText(user.userName);
        holder.tvId.setText(user.id);
        holder.tvAge.setText(user.age);
    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvUsername,tvAge,tvId;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvId = itemView.findViewById(R.id.tvId);
        }
    }
}
