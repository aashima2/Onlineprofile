package com.example.onlineprofile;

import android.provider.BaseColumns;

public class Educationcontract  {
    public  static  final  String TABLE_NAME3 = "Education";

    public  static class  Columns {
        public   static  final String _ID = BaseColumns._ID;

        public  static  final String Institution = " Institution ";
        public  static  final String  Study = " Study ";
        public  static  final String  StartYear = " StartYear ";
        public  static  final String  EndYear = " EndYear ";
        public  static  final String  Description = " Description ";

        private  Columns() {

        }
    }

}
