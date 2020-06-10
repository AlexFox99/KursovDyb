package com.example.a.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "date_table")
public class Date1 {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date1")
    private String date1;

    public Date1(@NonNull String date1){this.date1=date1;}
    public String getDate1(){return this.date1;}
}
