package com.example.a.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private Date1Dao mDate1Dao;
    private Date2Dao mDate2Dao;
    private RatingDao mRatingDao;
    private LiveData<List<Date1>> mAllDate1;
    private LiveData<List<Date2>> mAllDate2;
    private LiveData<List<Rating>> mAllRating;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
        mDate1Dao=db.date1Dao();
        mAllDate1 = mDate1Dao.getAllDate1();
        mDate2Dao=db.date2Dao();
        mAllDate2 = mDate2Dao.getAllDate2();
        mRatingDao = db.ratingDao();
        mAllRating = mRatingDao.getAllRating();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
    LiveData<List<Date1>> getmAllDate1(){return mAllDate1;}
    LiveData<List<Date2>> getmAllDate2(){return mAllDate2;}
    LiveData<List<Rating>> getmAllRating(){return mAllRating;}
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }
    public void insert(Date1 date1){new insertAsyncTask1(mDate1Dao).execute(date1);}
    public void insert(Date2 date2){new insertAsyncTask2(mDate2Dao).execute(date2);}
    public void insert(Rating rating){new insertAsyncTask3(mRatingDao).execute(rating);}
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask1 extends AsyncTask<Date1,Void,Void>{
        private Date1Dao mAsyncTaskDao;
        insertAsyncTask1(Date1Dao dao){mAsyncTaskDao=dao;}
        @Override
        protected Void doInBackground(final Date1... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask2 extends AsyncTask<Date2,Void,Void> {
        private Date2Dao mAsyncTaskDao;

        insertAsyncTask2(Date2Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Date2... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask3 extends AsyncTask<Rating,Void,Void>{
        private RatingDao mAsyncTaskDao;
        insertAsyncTask3(RatingDao dao){mAsyncTaskDao=dao;}
        @Override
        protected Void doInBackground(final Rating... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

        }
}

