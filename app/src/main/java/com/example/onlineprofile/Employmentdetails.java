package com.example.onlineprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Employmentdetails extends AppCompatActivity {
    Button save;
     AppDatabase mydb;
    EditText Designation;
      EditText Organisation;
      EditText startyear;
      EditText  Workedtill;
      EditText Description;
    int workedtill;
    int startyear1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employmentdetails);
         mydb = new AppDatabase(this);
        Designation = (EditText) findViewById(R.id.Designation);
        Organisation = (EditText) findViewById(R.id.Organisation);
         startyear = (EditText) findViewById(R.id.startyear);
         Workedtill = (EditText) findViewById(R.id.workedtill);
         Description = (EditText) findViewById(R.id.Jobdescription);
        final String username = getIntent().getStringExtra("usernames");

        final  String id = mydb.getid(username);
        Toast.makeText(this, "id" +  id  + " " + username, Toast.LENGTH_SHORT).show();

         save = (Button) findViewById(R.id.saveemployeedetails) ;

         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 workedtill = Integer.parseInt(Workedtill.getText().toString());
                 startyear1 = Integer.parseInt(startyear.getText().toString());
                 if(workedtill-startyear1 >= 0){
                     boolean insert = mydb.insertemployement(id, Designation.getText().toString(), Organisation.getText().toString(), startyear.getText().toString(), Workedtill.getText().toString(), Description.getText().toString());
                     if (insert == true) {
                         Intent intent = new Intent(Employmentdetails.this, Usersprofile.class);
                         intent.putExtra("usernames", username);
                         intent.putExtra("id", id);
                         Toast.makeText(Employmentdetails.this, " Inserted successfully", Toast.LENGTH_SHORT).show();
                         startActivity(intent);
                         finish();


                     } else {
                         Toast.makeText(Employmentdetails.this, " Not Inserted successfully", Toast.LENGTH_SHORT).show();

                     }

                 }else{
                     Toast.makeText(Employmentdetails.this, " Check the year entered", Toast.LENGTH_SHORT).show();

                 }








             }
         });



    }
}
