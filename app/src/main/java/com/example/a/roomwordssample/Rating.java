package com.example.a.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "rating_table")
public class Rating {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "rating")
    private Float rating;

    public Rating(@NonNull Float rating){this.rating=rating;}
    public Float getRating(){return this.rating;}
}
