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

public class editemployementdata extends AppCompatActivity {
    AppDatabase mydb;
    Button Edit;
  EditText designation;
 EditText Organisation;
  EditText Startyear;
  EditText Workedtill;
  EditText Describe;
  String org;
  String strt_yr;
  String  Desc;
    String name;
    String id;
    String workedtill;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deleteemp, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id1 = item.getItemId();
        if(id1 == R.id.Deletemp){
            mydb = new AppDatabase(this);
            Toast.makeText(this, "usernames" +  ":" + name, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "id" + id + ":" + name, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent (editemployementdata.this,Usersprofile.class);
            intent.putExtra("usernames",name);
            intent.putExtra("id",id);
           final String des = getIntent().getStringExtra("des");
            Toast.makeText(this, "designation" + des + id + ":" + name, Toast.LENGTH_SHORT).show();

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle(" Delete");
            b.setMessage(des + ":" +  "'s Do you Want to delete the Data of This user ?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean delete = mydb.deleteEmpdata(des);
                    if(delete == true){
                        Intent intent = new Intent(editemployementdata.this,Usersprofile.class);
                        intent.putExtra("usernames",name);
                        intent.putExtra("id",id);
                        intent.putExtra("des",des);
                        startActivity(intent);
                        finish();
                        Toast.makeText(editemployementdata.this, "successfully deleted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(editemployementdata.this, "not deleted", Toast.LENGTH_SHORT).show();
                    }

                }
            });b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                }
            });b.show();




        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemployementdata);
        mydb = new AppDatabase(this);
        Edit = (Button) findViewById(R.id.Editemployment);
        designation = (EditText) findViewById(R.id.edit2Designation);
        Organisation = (EditText) findViewById(R.id.edit2Organisation);
        Startyear = (EditText) findViewById(R.id.edit2startyear);
        Workedtill = (EditText) findViewById(R.id.edit2workedtill);
        Describe = (EditText) findViewById(R.id.edit2Jobdescription);

        name = getIntent().getStringExtra("usernames");
        Toast.makeText(this, "usernames" + name, Toast.LENGTH_SHORT).show();
         id = getIntent().getStringExtra("id");
        Toast.makeText(this, "id" + id + ":" + name, Toast.LENGTH_SHORT).show();

       final String des = getIntent().getStringExtra("des");
        Toast.makeText(this, "designation" + des, Toast.LENGTH_SHORT).show();

        Cursor cursor = mydb.getEmploymentEditdata(des);
        if(cursor == null){
            Toast.makeText(this, " No data ....", Toast.LENGTH_SHORT).show();
        }else{
            org = cursor.getString(2);
            strt_yr = cursor.getString(3);
            workedtill = cursor.getString(4);
            Desc = cursor.getString(5);

        }
        designation.setText(des);
        Organisation.setText(org);
        Startyear.setText(strt_yr);
        Workedtill.setText(workedtill);
        Describe.setText(Desc);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = mydb.updateEmpdata(id,designation.getText().toString(),Organisation.getText().toString(),Startyear.getText().toString(),
                        Workedtill.getText().toString(),Describe.getText().toString());
                if (update == true) {
                    Toast.makeText(editemployementdata.this, "Edited successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(editemployementdata.this,Usersprofile.class);
                    intent.putExtra("usernames",name);
                    intent.putExtra("id",id);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(editemployementdata.this, " Not Edited successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
