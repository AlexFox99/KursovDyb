package com.example.a.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface Date1Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Date1 date1);

    @Query("DELETE FROM date_table")
    void deleteAll();


    @Query("SELECT * from date_table ORDER BY date1 ASC")
    LiveData<List<Date1>> getAllDate1();
}
