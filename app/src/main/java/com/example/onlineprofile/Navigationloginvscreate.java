package com.example.onlineprofile;

import android.app.ActionBar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Navigationloginvscreate extends AppCompatActivity {

    Button login1;
    Button createacc;
    AppDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationloginvscreate);


        mydb = new AppDatabase(this);
        login1 = (Button) findViewById(R.id.navigate_to_login);
        createacc = (Button) findViewById(R.id.navigate_to_create_account);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigationloginvscreate.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigationloginvscreate.this,usersdetails.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
