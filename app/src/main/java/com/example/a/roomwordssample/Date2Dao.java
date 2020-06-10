package com.example.a.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Date2Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Date2 date2);

    @Query("DELETE FROM date2_table")
    void deleteAll();


    @Query("SELECT * from date2_table ORDER BY date2 ASC")
    LiveData<List<Date2>> getAllDate2();
}
