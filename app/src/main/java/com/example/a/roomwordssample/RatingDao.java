package com.example.a.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Rating rating);

    @Query("DELETE FROM rating_table")
    void deleteAll();


    @Query("SELECT * from rating_table ORDER BY rating ASC")
    LiveData<List<Rating>> getAllRating();
}
