package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.model.User;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText tetUsername;

    @BindView(R.id.tetPassword)
    TextInputEditText tetPassword;

    FirebaseDatabase database;
    DatabaseReference loginReference;

    int value = 0;

    @OnClick(R.id.btSignIn)
    public void signInUser() {

        // Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
        // Intent intent =new Intent(this,AdminDashboardActivity.class);
        //  startActivity(intent);

        writeLoginDetails();
        loginUser();

    }

    private void writeLoginDetails() {
        String userName = tetUsername.getText().toString();
        String password = tetPassword.getText().toString();

        ++value;

        String userId = Integer.toString(value);

        User user = new User(userName, password, userId);

        loginReference.child(user.id).setValue(user);
    }

    private void loginUser() {
        Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initiateFirebase();
        tetUsername = findViewById(R.id.tetUserName);


/*
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void initiateFirebase() {
        database = FirebaseDatabase.getInstance();
        loginReference = database.getReference("loginDetails");
    }


}
