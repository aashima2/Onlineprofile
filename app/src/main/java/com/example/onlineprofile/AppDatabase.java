package com.example.onlineprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "Appdatabase";

    public static final String DATABASE_NAME = "profile Creation.db";

    public  static final int  DATABASE_VERSION = 7;

    public static AppDatabase instance = null;

   public  AppDatabase(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
       Log.d(TAG, "AppDatabase: Constructor");
   }

    static AppDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new AppDatabase(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate:Starts");
       String sSql1;
        String sSql2;
        String sSql3;
        String sSql4;

       sSql1 = " CREATE  TABLE " + UsersContract.TABLE_NAME1 + "("
               + UsersContract.Columns._ID  + " INTEGER  PRIMARY KEY NOT NULL , "
               + UsersContract.Columns.name + " TEXT NOT NULL , "
               + UsersContract.Columns.UserName + " TEXT NOT NULL , "
               + UsersContract.Columns.Password + " TEXT NOT NULL );";
        Log.d(TAG,sSql1);
        db.execSQL(sSql1);

        Log.d(TAG, "onCreate: ends");

        sSql2 = " CREATE TABLE " + EmployeeContract.TABLE_NAME2 + "("
                + EmployeeContract.Columns._ID + " INTEGER NOT NULL ,"
                + EmployeeContract.Columns.Designation + " TEXT  NOT NULL ,"
                + EmployeeContract.Columns.Organisation + " TEXT NOT NULL , "
                + EmployeeContract.Columns.StartYear + " INTEGER NOT NULL , "
                + EmployeeContract.Columns.WorkedTill + " TEXT NOT NULL , "
                + EmployeeContract.Columns.Describe + " TEXT NOT NULL ); ";
        Log.d(TAG, sSql2);
        db.execSQL(sSql2);

        sSql3 = " CREATE TABLE " + Educationcontract.TABLE_NAME3 + "("
                + Educationcontract.Columns._ID + " INTEGER NOT NULL ,"
                + Educationcontract.Columns.Institution + " TEXT NOT NULL ,"
                + Educationcontract.Columns.Study + " TEXT NOT NULL ,"
                + Educationcontract.Columns.StartYear + " INTEGER NOT NULL , "
                + Educationcontract.Columns.EndYear + " INTEGER NOT NULL , "
                + Educationcontract.Columns.Description + " TEXT NOT NULL );";
        Log.d(TAG,sSql3);
        db.execSQL(sSql3);

        sSql4 = " CREATE TABLE " + skillscontract.TABLE_NAME4 + "("
                + skillscontract.Columns._ID + " INTEGER NOT NULL ,"
                + skillscontract.Columns.Skills + " TEXT NOT NULL );";
        Log.d(TAG, sSql4);
        db.execSQL(sSql4);
   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: starts");
        db.execSQL("DROP TABLE IF EXISTS " + UsersContract.TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeContract.TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + Educationcontract.TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + skillscontract.TABLE_NAME4);


        onCreate(db);
   }

   // insertion in users table

    public  boolean insertusers(String _id , String name, String  username , String password){
       SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put(UsersContract.Columns._ID , _id );
        cv.put(UsersContract.Columns.name , name);
        cv.put(UsersContract.Columns.UserName , username);
        cv.put(UsersContract.Columns.Password , password);
        long result = db.insert(UsersContract.TABLE_NAME1,null,cv);
        if(result == -1)
        {
            return  false;
        }
        else{
            return  true;
        }


    }
  //  getting id


        public String  getid(String name){
       String res;
            SQLiteDatabase db  = this.getReadableDatabase();
            Cursor cursor = db.query(UsersContract.TABLE_NAME1, new String[]{ UsersContract.Columns._ID}, UsersContract.Columns.UserName +"=?", new String[]{name},
                    null,null,null);
            cursor.moveToFirst();
            if(cursor.getCount()>0){
                 res = cursor.getString(0);

            }else {
                res = "null";

            }

            return  res;
        }

        // getting data of Education
        public Cursor getEducationdata(String id){
            SQLiteDatabase db  = this.getReadableDatabase();
            Cursor cursor = db.query(Educationcontract.TABLE_NAME3, new String[] { Educationcontract.Columns._ID,Educationcontract.Columns.Institution,
                    Educationcontract.Columns.Study,  Educationcontract.Columns.StartYear,Educationcontract.Columns.EndYear,Educationcontract.Columns.Description }, Educationcontract.Columns._ID + "=?", new String[]{ id}, null, null, null, null);

            cursor.moveToFirst();
            return  cursor;
        }


// get data of Employment
    public Cursor getEmployementdata(String id){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.query(EmployeeContract.TABLE_NAME2, new String[] {EmployeeContract.Columns._ID,EmployeeContract.Columns.Designation,
                EmployeeContract.Columns.Organisation,  EmployeeContract.Columns.StartYear,  EmployeeContract.Columns.WorkedTill,EmployeeContract.Columns.Describe},  EmployeeContract.Columns._ID + "=?", new String[]{ id}, null, null, null, null);

        cursor.moveToFirst();
        return  cursor;
    }
    // get data of Employement on  basis of designation
    public  Cursor getEmploymentEditdata(String designation){
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.query(EmployeeContract.TABLE_NAME2, new String[] {EmployeeContract.Columns._ID,EmployeeContract.Columns.Designation,
       EmployeeContract.Columns.Organisation, EmployeeContract.Columns.StartYear,EmployeeContract.Columns.WorkedTill,EmployeeContract.Columns.Describe},  EmployeeContract.Columns.Designation + "=?", new String[]{designation}, null,null,null,null);
       cursor.moveToFirst();
       return cursor;
    }





//  get data of  Education directly on basis of institution
public Cursor getEducationEditdata(String id , String ins){
    SQLiteDatabase db  = this.getReadableDatabase();
    Cursor cursor = db.query(Educationcontract.TABLE_NAME3, new String[] { Educationcontract.Columns._ID,Educationcontract.Columns.Institution,
            Educationcontract.Columns.Study,  Educationcontract.Columns.StartYear,Educationcontract.Columns.EndYear,Educationcontract.Columns.Description }, Educationcontract.Columns.Institution + "=?", new String[]{  ins}, null, null, null, null);

    cursor.moveToFirst();
    return  cursor;
}


// getting usernames
    public List<String> getUserName(){

        List <String> uname = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + UsersContract.TABLE_NAME1;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            Log.d(TAG, "getUserName: cursor started");
            do{
                uname.add(cursor.getString(2));
            }while(cursor.moveToNext());
            cursor.close();

        }
        return  uname;
    }

    // getting password
    String  getpass(String name){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.query(UsersContract.TABLE_NAME1, new String[]{ UsersContract.Columns.Password}, UsersContract.Columns.UserName +"=?", new String[]{name},
                null,null,null);
        cursor.moveToNext();

        String res = cursor.getString(0);
        return res;
    }
    // insertion of education details
    public boolean inserteducation( String id ,String institution, String study, String startyear , String endyear, String description){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       cv.put(Educationcontract.Columns._ID,id);
       cv.put(Educationcontract.Columns.Institution,institution);
       cv.put(Educationcontract.Columns.Study,study);
       cv.put(Educationcontract.Columns.StartYear,startyear);
       cv.put(Educationcontract.Columns.EndYear,endyear);
       cv.put(Educationcontract.Columns.Description,description);
       long result = db.insert(Educationcontract.TABLE_NAME3 , null, cv);
        if(result == -1)
        {
            return  false;
        }
        else{
            return  true;
        }
   }
//    // insertion of Employmentdetails
    public boolean insertemployement( String id , String designation, String organisation, String startyear, String workedtill,String description){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       cv.put(EmployeeContract.Columns._ID,id);
       cv.put(EmployeeContract.Columns.Designation,designation);
        cv.put(EmployeeContract.Columns.Organisation,organisation);
        cv.put(EmployeeContract.Columns.StartYear,startyear);
        cv.put(EmployeeContract.Columns.WorkedTill,workedtill);
        cv.put(EmployeeContract.Columns.Describe,description);
        long result = db.insert(EmployeeContract.TABLE_NAME2 , null, cv);
        if(result == -1)
        {
            return  false;
        }
        else{
            return  true;
        }
   }

    // Updation in Education details

    public  boolean updateEdudata(String id , String institution,String study,String startyear,String endyear,String description){
        Log.d(TAG, "updateEdudata: updation starts");
       SQLiteDatabase db = this.getReadableDatabase();
       ContentValues cv = new ContentValues();
        cv.put(Educationcontract.Columns._ID,id);
        cv.put(Educationcontract.Columns.Institution,institution);
        cv.put(Educationcontract.Columns.Study,study);
        cv.put(Educationcontract.Columns.StartYear,startyear);
        cv.put(Educationcontract.Columns.EndYear,endyear);
        cv.put(Educationcontract.Columns.Description,description);

        long result1 =  db.update(Educationcontract.TABLE_NAME3,cv,Educationcontract.Columns.Institution  +  "=?", new String[] {institution});
        Log.d(TAG, "updatedata: updation ends");
        return true;


    }
    // Updation in Education details

    public  boolean updateEmpdata( String id , String designation, String organisation, String startyear, String workedtill,String description){
        Log.d(TAG, "updateEmpdata: updation starts");
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EmployeeContract.Columns._ID,id);
        cv.put(EmployeeContract.Columns.Designation,designation);
        cv.put(EmployeeContract.Columns.Organisation,organisation);
        cv.put(EmployeeContract.Columns.StartYear,startyear);
        cv.put(EmployeeContract.Columns.WorkedTill,workedtill);
        cv.put(EmployeeContract.Columns.Describe,description);

        long result2 =  db.update(EmployeeContract.TABLE_NAME2,cv,EmployeeContract.Columns.Designation  +  "=?", new String[] {designation});
        Log.d(TAG, "updatedata: updation ends");
        return true;


    }
    // delete education data

    public  boolean deleteEdudata( String institution){
        Log.d(TAG, "deleteEdudata:  deletion started");
        SQLiteDatabase db = this.getWritableDatabase();
        long result3 = db.delete(Educationcontract.TABLE_NAME3,Educationcontract.Columns.Institution + "=?", new String[] {institution});
        Log.d(TAG, "deleteEdudata: deletion ends");
        return  true;
    }
    public  boolean deleteEmpdata( String designation){
        Log.d(TAG, "deleteEmpdata:  deletion started");
        SQLiteDatabase db = this.getWritableDatabase();
        long result3 = db.delete(EmployeeContract.TABLE_NAME2,EmployeeContract.Columns.Designation + "=?", new String[] {designation});
        Log.d(TAG, "deleteEmpdata: deletion ends");
        return  true;
    }
    // insertion for skills
    public  boolean insertskills(String id,String skills){
        Log.d(TAG, "insertskills: insertion starts");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
       cv.put(skillscontract.Columns._ID,id);
       cv.put(skillscontract.Columns.Skills,skills);
       long result4 = db.insert(skillscontract.TABLE_NAME4,null,cv);
        if(result4 == -1)
        {
            return  false;
        }
        else{
            return  true;
        }


}

// get skills data
//public List<String> getSkills(String id){
//
//   List  <String> skills = new ArrayList<>();
//
//    SQLiteDatabase db = this.getWritableDatabase();
//    String selectQuery = "SELECT  * FROM " + skillscontract.TABLE_NAME4 ;
//    Cursor cursor = db.rawQuery(selectQuery, null);
//    if(cursor.moveToFirst()){
//        Log.d(TAG, "getskills: cursor started");
//        do{
//            skills.add(cursor.getString(1));
//        }while(cursor.moveToNext());
//        cursor.close();
//
//    }
//    return  skills;
//}
    // get skills data
public Cursor getSkills(String id) {
    List<String> skills = new ArrayList<>();

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(skillscontract.TABLE_NAME4, new String[] { skillscontract.Columns._ID,skillscontract.Columns.Skills}, skillscontract.Columns._ID + "=?", new String[]{ id}, null, null, null, null);
    cursor.moveToFirst();
    return cursor;
}
//    public  boolean deleteskills( String id){
//        Log.d(TAG, "deleteEmpdata:  deletion started");
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result3 = db.delete(skillscontract.TABLE_NAME4,skillscontract.Columns._ID + "=?", new String[] {id});
//        Log.d(TAG, "deleteEmpdata: deletion ends");
//        return  true;
//    }
//    public String getskills1() {
//        String res;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * FROM " + skillscontract.TABLE_NAME4, null);
//        cursor.moveToFirst();
//        if(cursor.getCount()>0){
//            res = cursor.getString(1);
//
//        }else {
//            res = "null";
//
//        }
//
//        return  res;
//    }



    public  boolean deleteskills(String skills){
        Log.d(TAG, "deleteskills: deletion started");
        SQLiteDatabase db = this.getWritableDatabase();
        long resultt4 = db.delete(skillscontract.TABLE_NAME4,skillscontract.Columns.Skills + "=?", new String[]{skills});
        Log.d(TAG, "deleteskills: deletion ends");
        return  true;

    }



}
