package com.example.onlineprofile;

import android.provider.BaseColumns;

public class EmployeeContract {
    static  final String  TABLE_NAME2 = "Employee";

    public  static  class  Columns{
        public  static  final  String _ID = BaseColumns._ID;
        public static  final String Designation = " Designation ";
        public static  final String Organisation = " Organisation ";
        public static  final String  StartYear = " StartYear ";
        public  static final String   WorkedTill = " workedtill ";
        public static  final String   Describe = "describe ";
        private  Columns(){
        }
    }


}
