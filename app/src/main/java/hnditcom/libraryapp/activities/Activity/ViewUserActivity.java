package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.adapters.RecyclerAdapterUser;
import hnditcom.libraryapp.activities.model.User;

public class ViewUserActivity extends AppCompatActivity {

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

RecyclerAdapterUser recyclerAdapterUser;

ArrayList<User> userArrayList;




@BindView(R.id.rcUser)
RecyclerView rcUser;

@OnClick(R.id.fab)
public void addNewUser(){
    Intent intent = new Intent(this, CreateUserActivity.class);
    startActivity(intent);
}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        ButterKnife.bind(this);
        initializeFirebase();

        setRecyclerView();
        getUserList();
       // getNameFromDatabase();
       // getUserFromDatabase();
    }

    private void setRecyclerView() {

       /* for(int i=0;i<100;i++){
            User user = new User();
            user.userName = "Dummy username "+i;
            user.id = "Dummy id "+i;
            user.age= "Dummy age "+i;
            userArrayList.add(user);
        }*/

        recyclerAdapterUser = new RecyclerAdapterUser(userArrayList);
        rcUser.setAdapter(recyclerAdapterUser);
        rcUser.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getUserList() {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                userArrayList.add(user);
                recyclerAdapterUser.notifyDataSetChanged();
               // tvName.setText(user.userName+"\n "+"password is"+user.password);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (userArrayList.remove(user)){
                    recyclerAdapterUser.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUserFromDatabase() {
        DatabaseReference testReference = firebaseDatabase.getReference("testRead1");
        testReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getNameFromDatabase() {
        DatabaseReference testReference = firebaseDatabase.getReference("testRead");
        testReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userData");
        userArrayList = new ArrayList<>();
    }


}
