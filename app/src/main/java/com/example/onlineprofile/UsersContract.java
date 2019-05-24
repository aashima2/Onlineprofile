package com.example.onlineprofile;

import android.provider.BaseColumns;

public class UsersContract {

    static  final String  TABLE_NAME1 = "users";

    public  static class  Columns{
        public  static  final String _ID = BaseColumns._ID;
        public  static  final String name  = " Name ";
        public  static  final String UserName = " UserName ";
        public  static  final String Password = " Password ";

        private Columns() {

        }


    }


}
