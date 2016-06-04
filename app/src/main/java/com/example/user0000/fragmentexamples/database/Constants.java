package com.example.user0000.fragmentexamples.database;

import android.provider.BaseColumns;

/**
 * Created by user0000 on 6/3/2016.
 */
public class Constants {



    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME= "USERS";
        public static final String USER_NAME = "username";
        public static final String PASSWORD = "password";
    }
    public static final class Transactions implements BaseColumns{
        public static final String TABLE_NAME= "transaction";
        public static final String WEEK_NUMBER = "week_number";
        public static final String CHECK_IN = "check_in";
        public static final String CHECK_OUT= "check_out";
        public static final String USER_ID= "user_id";
        public static final String DATE = "date";

    }
}
