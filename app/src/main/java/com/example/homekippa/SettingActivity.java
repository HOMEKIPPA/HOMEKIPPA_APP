package com.example.homekippa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homekippa.data.ExitResponse;
import com.example.homekippa.data.GroupData;
import com.example.homekippa.data.UserData;
import com.example.homekippa.ui.group.ModifyGroupActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    private UserData userData;
    private Button button_ExitKippa;
    private Button button_EditUser;
    private Button button_EditGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        button_ExitKippa = findViewById(R.id.button_ExitKippa);
        button_EditGroup = findViewById(R.id.button_EditGroup);
        button_EditUser = findViewById(R.id.button_EditUser);
        Intent intent = getIntent();
        UserData userData = (UserData) intent.getExtras().get("userData");

        if(userData.getGroupId() != 0){
            button_EditGroup.setVisibility(View.VISIBLE);
        }else{
            button_EditGroup.setVisibility(View.GONE);
        }

        button_ExitKippa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ExitKippaActivity.class);
                intent.putExtra("userData", userData);
                 startActivity(intent);

            }
        });

        button_EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EditUserActivity.class);
                intent.putExtra("userData", userData);
                startActivity(intent);

            }
        });

        button_EditGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ModifyGroupActivity.class);
                startActivity(intent);

            }
        });
    }
}
