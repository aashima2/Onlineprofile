package com.example.onlineprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button Login;
    AppDatabase mydb;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new AppDatabase(this);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        Login = (Button) findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> names =  mydb.getUserName();
                String id = mydb.getid(username.getText().toString());
                if(names.contains(username.getText().toString())) {
                    if (mydb.getpass(username.getText().toString()).equals(password.getText().toString())) {

                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        // login code has to be written
                        Intent intent = new Intent(MainActivity.this,Usersprofile.class);
                        intent.putExtra("usernames",username.getText().toString());
                        intent.putExtra("id",id);
                        startActivity(intent);
                        finish();

                    } else{
                        Toast.makeText(MainActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "User not present", Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(MainActivity.this,Navigationloginvscreate.class);
//
//                    Toast.makeText(MainActivity.this, "  Already user created ....", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }





//                Intent intent = new Intent(MainActivity.this,Educationdetails.class);
//                startActivity(intent);
            }
        });

    }
}
