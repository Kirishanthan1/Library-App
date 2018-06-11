package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.model.User;

public class CreateUserActivity extends AppCompatActivity {

    @BindView(R.id.tetName)
    TextInputEditText tetName;

    @BindView(R.id.tetAge)
    TextInputEditText tetAge;

    @BindView(R.id.tetId)
    TextInputEditText tetId;

    @BindView(R.id.tetPassword)
    TextInputEditText tetPassword;

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
        userDatabaseReference.child(user.id).setValue(user);
    }

    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        initiateFirebase();

    }

    private void initiateFirebase() {
        database = FirebaseDatabase.getInstance();
        userDatabaseReference = database.getReference("userData");
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
