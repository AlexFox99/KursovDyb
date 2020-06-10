package com.example.a.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Date1>> mAllDate1;
    private LiveData<List<Date2>> mAllDate2;
    private LiveData<List<Rating>> mAllRating;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mAllDate1 = mRepository.getmAllDate1();
        mAllDate2 = mRepository.getmAllDate2();
        mAllRating = mRepository.getmAllRating();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }
    LiveData<List<Date1>> getmAllDate1(){return  mAllDate1;}
    LiveData<List<Date2>> getmAllDate2(){return mAllDate2;}
    LiveData<List<Rating>> getmAllRating(){return mAllRating;}

    public void insert(Word word) { mRepository.insert(word); }
    public void insert(Date1 date1){mRepository.insert(date1);}
    public void insert(Date2 date2){mRepository.insert(date2);}
    public void insert(Rating rating){mRepository.insert(rating);}

}
