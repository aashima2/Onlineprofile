package com.example.onlineprofile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editeducation extends AppCompatActivity {
    AppDatabase mydb;
    EditText Institutename;
    EditText study;
    EditText startyear;
    EditText endyear;
    EditText Description;
    Button Edit;
    String Ins;
    String stu;
    String st_yr;
    String end_yr;
    String des;
    String name;
    String id;
    int endyear1;
    int startyear1;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deletion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id1 = item.getItemId();
        if(id1 == R.id.Deletedu){
            mydb = new AppDatabase(this);
            Toast.makeText(this, "usernames" +  ":" + name, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "id" + id + ":" + name, Toast.LENGTH_SHORT).show();


            Intent intent = new Intent (Editeducation.this,Usersprofile.class);
            intent.putExtra("usernames",name);
            intent.putExtra("id",id);

            Ins = getIntent().getStringExtra("Ins");
            Toast.makeText(this, "Ins" + Ins + ":" + name, Toast.LENGTH_SHORT).show();
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle(" Delete");
            b.setMessage(name + ":" +  "'s Do you Want to delete the Data of This user ?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  boolean delete = mydb.deleteEdudata(Ins);
                  if(delete == true){
                      Intent intent = new Intent(Editeducation.this,Usersprofile.class);
                      intent.putExtra("usernames",name);
                      intent.putExtra("id",id);
                      intent.putExtra("Ins",Ins);
                      startActivity(intent);
                     finish();
                      Toast.makeText(Editeducation.this, "successfully deleted", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(Editeducation.this, "not deleted", Toast.LENGTH_SHORT).show();
                  }

                }
            });b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                }
            });b.show();




//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeducation);
        mydb = new AppDatabase(this);
        Institutename = (EditText) findViewById(R.id.edit1_universityname);
        study = (EditText) findViewById(R.id.edit1_fieldofstudy);
        startyear = (EditText) findViewById(R.id.edit1_startyear);
        endyear = (EditText) findViewById(R.id.edit1_endyear);
        Description = (EditText) findViewById(R.id.edit1_Description);

         name = getIntent().getStringExtra("usernames");
        Toast.makeText(this, "usernames" + name, Toast.LENGTH_SHORT).show();
       id = getIntent().getStringExtra("id");
        Toast.makeText(this, "id" + id + ":" + name, Toast.LENGTH_SHORT).show();

         Ins = getIntent().getStringExtra("Ins");
        Toast.makeText(this, "Ins" + Ins + ":" + name, Toast.LENGTH_SHORT).show();
        Cursor cursor =  mydb.getEducationEditdata(id,Ins);
        if(cursor == null){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
          stu = cursor.getString(2);
          st_yr = cursor.getString(3);
          end_yr = cursor.getString(4);
          des = cursor.getString(5);

        }
        Institutename.setText(Ins);
        study.setText(stu);
        startyear.setText(st_yr);
        endyear.setText(end_yr);
        Description.setText(des);

        Edit = (Button) findViewById(R.id.btn_edit_edu_detail);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endyear1 = Integer.parseInt(endyear.getText().toString());
                startyear1 = Integer.parseInt(startyear.getText().toString());
                if(endyear1 - startyear1 > 2) {

                    boolean update = mydb.updateEdudata(id, Institutename.getText().toString(), study.getText().toString(), startyear.getText().toString(),
                            endyear.getText().toString(), Description.getText().toString());
                    if (update == true) {
                        Toast.makeText(Editeducation.this, "Edited successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Editeducation.this, Usersprofile.class);
                        intent.putExtra("usernames", name);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Editeducation.this, " Not Edited successfully", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Editeducation.this, " Check the year entered ", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
