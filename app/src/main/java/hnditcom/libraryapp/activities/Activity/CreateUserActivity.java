package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.model.User;
import hnditcom.libraryapp.activities.utility.Contants;
import hnditcom.libraryapp.activities.utility.FirebaseReference;

public class CreateUserActivity extends AppCompatActivity {


    FirebaseReference firebaseReference;

    @BindView(R.id.tetName)
    TextInputEditText tetName;

    @BindView(R.id.tetAge)
    TextInputEditText tetAge;

    @BindView(R.id.tetId)
    TextInputEditText tetId;

    @BindView(R.id.tetPassword)
    TextInputEditText tetPassword;

    String username;
    String password;
    String id;
    String age;

    @BindView(R.id.btSave)
    Button btCreateUser;

    @BindView(R.id.btUpdate)
    Button btUpdateUser;


    FirebaseDatabase database;
    DatabaseReference userDatabaseReference;



    @OnClick(R.id.btSave)
        public void createNewUser() {

        User user = new User();
        user.userName = tetName.getText().toString();
        user.password = tetPassword.getText().toString();
        user.age = tetAge.getText().toString();
        user.id = tetId.getText().toString();

         if (!user.id.isEmpty()){
        //User user = new User(getUsername(),getPassword(),getAge(),getId());
        userDatabaseReference.child(user.id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                tetName.setText("");
                tetPassword.setText("");
                tetAge.setText("");
                tetId.setText("");
                Toast.makeText(CreateUserActivity.this,"User added successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        getIntentData();
        initiateFirebase();
        firebaseReference = new FirebaseReference();

    }

    @OnClick(R.id.btUpdate)
    public void upDateUser(){
        User user = new User(tetName.getText().toString(),
                             tetPassword.getText().toString(),
                            id,
                              tetAge.getText().toString());
        firebaseReference.upDateUser(user);
        finish();
    }

    private void getIntentData() {
        username = getIntent().getStringExtra(Contants.KEY_USERNAME);
        if(username != null){
           // Toast.makeText(this,"User stsrted this activity with username of"+username,Toast.LENGTH_LONG).show();
            password = getIntent().getStringExtra(Contants.KEY_PASSWORD);
            age = getIntent().getStringExtra(Contants.KEY_AGE);
            id = getIntent().getStringExtra(Contants.KEY_ID);

            tetName.setText(username);
            tetPassword.setText(password);
            tetAge.setText(age);
            tetId.setText(id);

            btUpdateUser.setVisibility(View.VISIBLE);
        }
        else {
           // Toast.makeText(this,"User started this activity without extra values",Toast.LENGTH_LONG).show();
            btCreateUser.setVisibility(View.VISIBLE);
        }
    }

    private void initiateFirebase() {
        database = FirebaseDatabase.getInstance();
        userDatabaseReference = database.getReference(Contants.USER_DATABASE_REFERENCE);
    }
    private String getUsername(){
        return tetName.getText().toString();
    }

    private String getPassword(){
        return tetPassword.getText().toString();
    }

    private String getAge(){
        return tetAge.getText().toString();
    }

    private String getId(){
        return tetId.getText().toString();
    }
}
