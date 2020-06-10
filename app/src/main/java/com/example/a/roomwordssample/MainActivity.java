package com.example.a.roomwordssample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });
        mWordViewModel.getmAllDate1().observe(this, new Observer<List<Date1>>() {
            @Override
            public void onChanged(@Nullable final List<Date1> date1s) {
                adapter.setDate1(date1s);
            }
        });
        mWordViewModel.getmAllDate2().observe(this, new Observer<List<Date2>>() {
            @Override
            public void onChanged(List<Date2> date2s) {
                adapter.setDate2(date2s);
            }
        });
        mWordViewModel.getmAllRating().observe(this, new Observer<List<Rating>>() {
            @Override
            public void onChanged(List<Rating> ratings) {
                adapter.setRating(ratings);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            //mWordViewModel.insert(word);
            String a = new String();
            a=data.getStringExtra(NewWordActivity.EXTRA_REPLY);
            Log.i("TextGSON", a);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Information_InGson information = gson.fromJson(a, Information_InGson.class);
            Log.i("GSON2", "Имя: " + information.Name + "\ndate1: " + information.nDate1 + "\ndate2: " +
                    information.nDate2+"\nrating: "+information.nRating);
            Word word = new Word(information.Name);
            mWordViewModel.insert(word);
            Date1 date1 = new Date1(information.nDate1);
            mWordViewModel.insert(date1);
            Date2 date2 = new Date2(information.nDate2);
            mWordViewModel.insert(date2);
            Rating rating = new Rating(Float.valueOf(information.nRating));
            Log.i("GSON111",Float.valueOf(information.nRating).toString());
            mWordViewModel.insert(rating);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
