package com.example.onlineprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class skills extends AppCompatActivity {
    AppDatabase mydb;
    Button submit;
    EditText skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        mydb = new AppDatabase(this);

        submit = (Button) findViewById(R.id.submit);
        skills = (EditText) findViewById(R.id.editskills);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = getIntent().getStringExtra("usernames");


                final  String id = mydb.getid(username);
                Toast.makeText(skills.this, " id" + id +":" + username, Toast.LENGTH_SHORT).show();

                boolean insert =  mydb.insertskills(id, skills.getText().toString());
                if(insert == true){
                    Intent intent = new Intent(skills.this,Usersprofile.class);
                    intent.putExtra("usernames", username);
                    intent.putExtra("id",id);
                    Toast.makeText(skills.this, " Inserted successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(skills.this, "  Not Inserted successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
