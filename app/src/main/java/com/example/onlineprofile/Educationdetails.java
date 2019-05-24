package com.example.onlineprofile;

import android.content.Intent;
import android.graphics.DiscretePathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class Educationdetails extends AppCompatActivity {
    Button Save;
    AppDatabase mydb;
    EditText institution;
    EditText  study;
    EditText  startyear;
    EditText endyear;
    EditText Description;
    int endyear1;
    int startyear1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educationdetails);
        mydb = new AppDatabase(this);
        institution = (EditText) findViewById(R.id.et_universityname);
        study = (EditText) findViewById(R.id.et_fieldofstudy);
        startyear = (EditText) findViewById(R.id.et_startyear);
        endyear = (EditText) findViewById(R.id.et_endyear);
        Description = (EditText) findViewById(R.id.et_Description);
        final String username = getIntent().getStringExtra("usernames");

        final  String id = mydb.getid(username);
        Toast.makeText(this, "id" + id +   " " +  username, Toast.LENGTH_SHORT).show();


        Save = (Button) findViewById(R.id.btn_insert_edu_detail) ;
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endyear1 = Integer.parseInt(endyear.getText().toString());
                startyear1 = Integer.parseInt(startyear.getText().toString());

                if(endyear1 - startyear1 > 2){
                boolean insert = mydb.inserteducation(id,institution.getText().toString(),study.getText().toString(),startyear.getText().toString(),endyear.getText().toString(),Description.getText().toString());
                if(insert == true) {
                    Toast.makeText(Educationdetails.this, " Inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Educationdetails.this, Usersprofile.class);
                    intent.putExtra("usernames",username);
                    intent.putExtra("id",id);
                    Toast.makeText(Educationdetails.this, "id" + id, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(Educationdetails.this, " Not inserted successfully", Toast.LENGTH_SHORT).show();
                }
                }else{
                    Toast.makeText(Educationdetails.this, " Check the year entered", Toast.LENGTH_SHORT).show();

                }
           }
        });




    }
}
