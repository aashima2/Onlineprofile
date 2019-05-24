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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Usersprofile extends AppCompatActivity implements onlineprofileAdapter.customButtonListener {

    public  static  final  String  TAG = "Users profile";
    onlineprofileAdapter adapter;
    AppDatabase mydb;
    TextView title;
    TextView description;
    TextView Add;
    ListView ls;
    ListView ls1;
    ListView ls2;
onlineprofileAdapter2 adapter2;
  TextView Addedu;
TextView Addemp;
TextView Addskiils;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout){
            Intent intent = new Intent(Usersprofile.this,Navigationloginvscreate.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersprofile);

        ls = (ListView) findViewById(R.id.listview);
        ls1 = (ListView) findViewById(R.id.listview1);
        ls2 = (ListView) findViewById(R.id.listview2);
        Addedu = (TextView) findViewById(R.id.Adddetails);
        Addemp = (TextView) findViewById(R.id.addEmpdetails);
        Addskiils=(TextView) findViewById(R.id.AddSkills);



        mydb = new AppDatabase(this);
       final String name = getIntent().getStringExtra("usernames");
        final String id = getIntent().getStringExtra("id");

        final Cursor cursor = mydb.getEducationdata(id);
        final Cursor cursor1 = mydb.getEmployementdata(id);
        Cursor cursor2 = mydb.getSkills(id);
        final List <String> List= new ArrayList<>();
        if(cursor2.getCount()>0){
            do {
                List.add(cursor2.getString(1));



            }while (cursor2.moveToNext());
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List);
            ls2.setAdapter(listAdapter);
        }


        ls2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id1) {
//                Toast.makeText(Usersprofile.this, " id " + id + "position"+ position, Toast.LENGTH_SHORT).show();
                final Cursor res = mydb.getSkills(id);
                Toast.makeText(Usersprofile.this, "skills" + List.get(position), Toast.LENGTH_SHORT).show();
                res.moveToPosition(position);
                Intent intent = new Intent(Usersprofile.this,Usersprofile.class);
                intent.putExtra("usernames",name);
                intent.putExtra("id",id);
                intent.putExtra("position",position);
                AlertDialog.Builder b = new AlertDialog.Builder(Usersprofile.this);
                b.setTitle(" Delete");
                b.setMessage(  List.get(position) +  "'s Do you Want to delete the skill ?");
                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Toast.makeText(Usersprofile.this, " skills" + List, Toast.LENGTH_SHORT).show();

                        boolean delete = mydb.deleteskills(List.get(position));
                        if(delete == true) {

                            Intent intent = new Intent(Usersprofile.this, Usersprofile.class);
                            intent.putExtra("usernames", name);
                            intent.putExtra("id", id);
                            intent.putExtra("position", position);
                            startActivity(intent);
                            finish();

                            Toast.makeText(Usersprofile.this, "Deleted skills" , Toast.LENGTH_SHORT).show();
                        }



                    }
                });b.show();

           }
        });

        if (cursor.getCount() > 0){


//            Toast.makeText(this, "Ins" + cursor.getString(1), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "cursorcount" + cursor.getCount(), Toast.LENGTH_SHORT).show();

            adapter = new onlineprofileAdapter(this,generatedata(),id,name);
            ls.setAdapter(adapter);


            Toast.makeText(this, "DataExits", Toast.LENGTH_SHORT).show();




        } if(cursor1.getCount()>0){
            adapter2 = new onlineprofileAdapter2(this,generatedata1(),id,name);
            ls1.setAdapter(adapter2);

//            Toast.makeText(this, "des" + cursor1.getString(1), Toast.LENGTH_SHORT).show();
        }



        Addedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Usersprofile.this,Educationdetails.class);

                intent.putExtra("usernames",name);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();

            }
        });

        Addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Usersprofile.this,Employmentdetails.class);

                intent.putExtra("usernames",name);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();

            }
        });

        Addskiils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Usersprofile.this,skills.class);
                intent.putExtra("usernames",name);
                Toast.makeText(Usersprofile.this, "usernames" + name, Toast.LENGTH_SHORT).show();
                intent.putExtra("id",id);
                Toast.makeText(Usersprofile.this, "id" + id, Toast.LENGTH_SHORT).show();
//                startActivity(intent);

                AlertDialog.Builder b = new AlertDialog.Builder(Usersprofile.this);
                b.setTitle(" ADD SKILLS");
                b.setMessage( id + ":" +  " 's Do you want to Add Skills.. ");
                b.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent  = new Intent(Usersprofile.this,skills.class);
                        intent.putExtra("usernames",name);
                        Toast.makeText(Usersprofile.this, "usernames" + name, Toast.LENGTH_SHORT).show();
                        intent.putExtra("id",id);
                        startActivity(intent);
                        finish();

                    }
                });b.show();
            }
        });




    }


    @Override
    public void onButtonClickListner(int position) {

    }

    @Override
    public void onButtonClickListner2(int position) {

    }
    private ArrayList<profilecreation> generatedata() {

        mydb = new AppDatabase(this);
        final String id = getIntent().getStringExtra("id");


        Cursor cursor = mydb.getEducationdata(id);
        Toast.makeText(this, "Ins" + cursor.getString(1), Toast.LENGTH_SHORT).show();

        ArrayList<profilecreation> items = new ArrayList<profilecreation>();

        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            do{
                items.add(new profilecreation(cursor.getString(1).toString(),R.id.Edit,cursor.getString(3),cursor.getString(4),cursor.getString(5).toString()));
//                Toast.makeText(this, " items" + cursor.getCount(), Toast.LENGTH_SHORT).show();

            }while (cursor.moveToNext());

        }
        return  items;

    }

    private ArrayList<profilecreation> generatedata1() {

        mydb = new AppDatabase(this);
        final String id = getIntent().getStringExtra("id");


        Cursor cursor1 = mydb.getEmployementdata(id);
//        Toast.makeText(this, "Des" + cursor1.getString(1), Toast.LENGTH_SHORT).show();

        ArrayList<profilecreation> items = new ArrayList<profilecreation>();

        if(cursor1.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }do{
            items.add(new profilecreation(cursor1.getString(1).toString(),R.id.Edit,cursor1.getString(3),cursor1.getString(4),cursor1.getString(5).toString()));
//            Toast.makeText(this, " items" + cursor1.getCount() , Toast.LENGTH_SHORT).show();

        }while (cursor1.moveToNext());
        return  items;

   }









}
