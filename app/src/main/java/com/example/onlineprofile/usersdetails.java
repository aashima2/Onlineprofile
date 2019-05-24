package com.example.onlineprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class usersdetails extends AppCompatActivity {
    EditText Name;
    EditText Username;
    EditText password;
    Button Save;
    AppDatabase mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersdetails);

//      final String name = getIntent().getStringExtra("usernames");
//        Toast.makeText(this, "usernames"+ name, Toast.LENGTH_SHORT).show();
        mydb = new AppDatabase(this);

        Name = (EditText) findViewById(R.id.et_Name);
        Username = (EditText) findViewById(R.id.et_username_insertion);
        password = (EditText) findViewById(R.id.et_password_insertion);
          final List<String> names =  mydb.getUserName();
          Toast.makeText(this, "username" + names, Toast.LENGTH_SHORT).show();




        Save = (Button) findViewById(R.id.btn_user_insertion);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(names.contains(Username.getText().toString())){
                    Intent intent = new Intent(usersdetails.this,Navigationloginvscreate.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(usersdetails.this, "User already exist please create it with different name", Toast.LENGTH_SHORT).show();

                }

                else{
                    Random rand = new Random();
                    int n = rand.nextInt(50);
                    boolean  insert =  mydb.insertusers(String.valueOf(n),Name.getText().toString(),Username.getText().toString(),
                            password.getText().toString());

                    if(insert == true){
                        Toast.makeText(usersdetails.this, " Successfully inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(usersdetails.this,MainActivity.class);
                        intent.putExtra("username",Username.getText().toString());
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(usersdetails.this, " NOt inserted successfully", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });








    }
}
