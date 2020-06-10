package com.example.a.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "date2_table")
public class Date2 {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date2")
    private String date2;

    public Date2(@NonNull String date2){this.date2=date2;}
    public String getDate2(){return this.date2;}
}
