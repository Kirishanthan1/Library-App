package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.model.User;
import hnditcom.libraryapp.activities.utility.FirebaseReference;

public class LoginActivity extends AppCompatActivity {
    private static final String ADMIN_ID = "000";
    private static final String ADMIN_PASSWORD = "admin";
    TextInputEditText tetUsername;

    @BindView(R.id.tetPassword)
    TextInputEditText tetPassword;

    //FirebaseDatabase database;
    //DatabaseReference loginReference;

    FirebaseReference firebaseReference;

    int value = 0;

    @OnClick(R.id.btSignIn)
    public void signInUser() {

        // Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
        // Intent intent =new Intent(this,AdminDashboardActivity.class);
        //  startActivity(intent);

       // writeLoginDetails();
        loginUser();

    }

   /* private void writeLoginDetails() {
        String userName = tetUsername.getText().toString();
        String password = tetPassword.getText().toString();

        ++value;

        String userId = Integer.toString(value);

        User user = new User(userName, password, userId);

        loginReference.child(user.id).setValue(user);
    }*/


    private void loginUser() {
        String id = getUsername();
        String password = getPassword();
      switch(id){
          case ADMIN_ID:
              if (id.contentEquals(ADMIN_ID)&& password.contentEquals(ADMIN_PASSWORD)){
                  startAdminPage();
              }
              break;
           default:
               firebaseReference.getUserDataReference().child(id).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       User user = dataSnapshot.getValue(User.class);
                       if(user!=null && user.id!=null && user.password!=null){
                           if(user.password.contentEquals(getPassword())){
                               Toast.makeText(LoginActivity.this,"login success",Toast.LENGTH_SHORT).show();
                           }
                       }

                       else {
                           Toast.makeText(LoginActivity.this,"login failed",Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
      }

        /*Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);*/
    }

    private void startAdminPage() {
        Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
       // initiateFirebase();
        tetUsername = findViewById(R.id.tetUserName);
        firebaseReference = new FirebaseReference();


/*
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /*private void initiateFirebase() {
        database = FirebaseDatabase.getInstance();
        loginReference = database.getReference("loginDetails");
    }*/


    public String getUsername() {
        return tetUsername.getText().toString();
    }

    public String getPassword() {
        return tetPassword.getText().toString();
    }
}

