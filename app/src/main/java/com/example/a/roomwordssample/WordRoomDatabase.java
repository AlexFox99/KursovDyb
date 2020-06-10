package com.example.a.roomwordssample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class , Date1.class , Date2.class, Rating.class}, version = 3)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    public abstract Date1Dao date1Dao();
    public abstract Date2Dao date2Dao();
    public abstract RatingDao ratingDao();
    private static WordRoomDatabase sInstance;

    static WordRoomDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (WordRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
// Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return sInstance;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
            };
    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        private final Date1Dao m2Dao;
        private final Date2Dao m3Dao;
        private final RatingDao m4Dao;
        //String[] words = {"dolphin", "crocodile", "cobra"};
        //String[] data1 = {"111", "222", "333"};
        //String[] data2 = {"11:11", "22:22", "33:33"};
        //Float[] rating = {2f,3f,4f};
        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
            m2Dao = db.date1Dao();
            m3Dao = db.date2Dao();
            m4Dao = db.ratingDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            m2Dao.deleteAll();
            m3Dao.deleteAll();
            m4Dao.deleteAll();


            return null;
        }
    }

}
