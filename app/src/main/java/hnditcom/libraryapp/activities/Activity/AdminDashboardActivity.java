package hnditcom.libraryapp.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hnditcom.libraryapp.R;
import hnditcom.libraryapp.activities.dialogs.AdminMemberAction;

public class AdminDashboardActivity extends AppCompatActivity {

    @OnClick(R.id.btAdmin)
    public void openManageUser(){

       /* AdminMemberAction adminMemberAction = new AdminMemberAction();
        adminMemberAction.show(getSupportFragmentManager(),"dialog");*/
        Intent intent = new Intent(this, ViewUserActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btManageBookInformation)
    public void openManageBook(){
        Intent intent = new Intent(this, ViewUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        ButterKnife.bind(this);

    }


}
