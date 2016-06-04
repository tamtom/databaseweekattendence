package com.example.user0000.fragmentexamples.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.user0000.fragmentexamples.database.Constants.UserEntry;
import com.example.user0000.fragmentexamples.database.Constants.Transactions;

import java.util.ArrayList;

/**
 * Created by user0000 on 6/3/2016.
 */
public class DatabaseAdapter {
    DataBaseHelper dataBaseHelper;

    public DatabaseAdapter(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public boolean insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserEntry.USER_NAME, user.getUserName());
        contentValues.put(UserEntry.PASSWORD, user.getPassword());
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.insert(UserEntry.TABLE_NAME, null, contentValues);
        return true;

    }

    public boolean insertRecored(Record recored) {
        ContentValues c = new ContentValues();
        c.put(Transactions.DATE, recored.getDate());
        c.put(Transactions.CHECK_IN, recored.getCheckIn());
        c.put(Transactions.CHECK_OUT, recored.getCheckOut());
        c.put(Transactions.WEEK_NUMBER, recored.getWeekNumber());
        c.put(Transactions.USER_ID, recored.getUser());
        return true;

    }

    public boolean updateRecored(Record recored) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(Transactions.CHECK_IN, recored.getCheckIn());
        c.put(Transactions.CHECK_OUT, recored.getCheckOut());
        int r = db.update(Transactions.TABLE_NAME, c, Transactions.DATE + " = ? AND " + Transactions.USER_ID + " = ?", new String[]{String.valueOf(recored.getDate()), String.valueOf(recored.getUser())});
        return r > 0;
    }

    public ArrayList<Record> getAllRecoredInWeek(Record record) {
        ArrayList<Record> records = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery
                ("select * from " + Transactions.TABLE_NAME + " where " +
                        Transactions.WEEK_NUMBER + " = ( select " + Transactions.WEEK_NUMBER +
                        " from " + Transactions.TABLE_NAME + " where " + Transactions.DATE + " = "
                        + record.getDate() + " AND " +
                        Transactions.USER_ID + " = "
                        + record.getUser() + " )", null);
        int dateindex = cursor.getColumnIndex(Transactions.DATE);
        int checkinindex = cursor.getColumnIndex(Transactions.CHECK_IN);
        int checkoutIndex = cursor.getColumnIndex(Transactions.CHECK_OUT);
        int weekNumberIndex = cursor.getColumnIndex(Transactions.WEEK_NUMBER);
        int userIdIndex = cursor.getColumnIndex(Transactions.USER_ID);
        if (cursor.moveToFirst()) {
            do {
                Record record1 = new Record();
                record1.setDate(cursor.getLong(dateindex));
                record1.setCheckIn(cursor.getLong(checkinindex));
                record1.setCheckOut(cursor.getLong(checkoutIndex));
                record1.setWeekNumber(cursor.getInt(weekNumberIndex));
                record.setUser(cursor.getInt(userIdIndex));
                // Adding contact to list
                records.add(record1);
            } while (cursor.moveToNext());
        }
return records;
    }

   private static class DataBaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "attendance.db";
        public static final int DATABASE_VERSION= 1;

          public  DataBaseHelper(Context context){
              super(context,DATABASE_NAME,null,DATABASE_VERSION);
          }
        @Override
        public void onCreate(SQLiteDatabase db) {
            final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserEntry.USER_NAME+ " TEXT UNIQUE NOT NULL, " +
                    UserEntry.PASSWORD+ " TEXT NOT NULL, " +
                    " );";
            final String SQL_CREATE_RECORED_TABLE = "CREATE TABLE " + Transactions.TABLE_NAME + " (" +
                    Transactions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Transactions.DATE+ " LONG, " +
                    Transactions.WEEK_NUMBER+ " INTEGER, " +
                    Transactions.CHECK_IN+ " LONG, "+
                    Transactions.CHECK_OUT+ " LONG "+
                    " FOREIGN KEY (" + Transactions.USER_ID + ") REFERENCES " +
                    UserEntry.TABLE_NAME + " (" + UserEntry._ID + "), " +

                    " );";


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    
}
