package hnditcom.libraryapp.activities.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.tvUsername.setText("Username : "+user.userName);
        holder.tvId.setText("Id : "+user.id);
        holder.tvAge.setText("Age : "+user.age);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = userArrayList.get(holder.getAdapterPosition());
                Toast.makeText(holder.itemView.getContext(),"Clicked User is "+user1.userName,Toast.LENGTH_SHORT).show();

            }
        });


        holder.btDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                User user1 = userArrayList.get(holder.getAdapterPosition());
                FirebaseDatabase.getInstance().getReference("userData")
                        .child(user1.id).setValue(null);

            }
        });


        holder.btEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


            }
        });

    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvUsername,tvAge,tvId;
        public Button btDelete,btEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvId = itemView.findViewById(R.id.tvId);

            btDelete = itemView.findViewById(R.id.btDelete);
            btEdit = itemView.findViewById(R.id.btEdit);
        }
    }
}
