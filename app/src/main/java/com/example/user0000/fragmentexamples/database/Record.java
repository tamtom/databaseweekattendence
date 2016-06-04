package com.example.user0000.fragmentexamples.database;

/**
 * Created by user0000 on 6/3/2016.
 */
public class Record {
    private long date;
    private int weekNumber;
    private long checkIn;
    private  long checkOut;
    private int user_id;

    public Record() {
    }

    public Record(long date, int weekNumber, long checkIn, long checkOut, int user) {
        this.date = date;
        this.weekNumber = weekNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.user_id = user;
    }

    public long getDate() {
        return date;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public long getCheckIn() {
        return checkIn;
    }

    public long getCheckOut() {
        return checkOut;
    }

    public int getUser() {
        return user_id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setCheckIn(long checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(long checkOut) {
        this.checkOut = checkOut;
    }

    public void setUser(int user) {
        this.user_id = user;
    }
}
