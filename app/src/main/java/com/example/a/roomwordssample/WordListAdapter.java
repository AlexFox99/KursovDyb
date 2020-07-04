package com.example.a.roomwordssample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private List<Date1> mDate1;
    private List<Date2> mDate2;
    private List<Rating> mRating;

    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if ((mWords != null)&&(mDate1 != null)&&(mDate2 != null)&&(mRating != null)) {
            Log.i("Curent","Шаг:"+String.valueOf(position));
            Word current = mWords.get(position);
            Log.i("Curent1","Шаг:"+String.valueOf(position)+" "+current+current.toString());
            holder.wordItemView.setText(current.getWord());
//            Log.i("Curent21","Шаг:"+String.valueOf(position)+" "+mDate1.get(position)+" Значение:"+current.getWord());
            Date1 current2 = mDate1.get(position);
            Log.i("Curent4","Шаг:"+String.valueOf(position)+" "+current2);
            holder.date1ItemView.setText(current2.getDate1());
            Log.i("Curent2","Шаг:"+String.valueOf(position)+" "+current2+mDate1+" Значение"+current2.getDate1());
            Date2 current3 = mDate2.get(position);
            Log.i("Curent4","Шаг:"+String.valueOf(position)+" "+current3);
            holder.date2ItemView.setText(current3.getDate2());
            Log.i("Curent4","Шаг:"+String.valueOf(position)+" "+current3+mDate1+" Значение"+current3.getDate2());
            Rating current4 = mRating.get(position);
            Log.i("Curent4","Шаг:"+String.valueOf(position)+" "+current4);
            holder.ratingItemView.setRating(current4.getRating());
            Log.i("Curent4","Шаг:"+String.valueOf(position)+" Значение:"+current4.getRating());

        }

    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
        Log.i("Curent3","Заход в ворд");
    }
    void setDate1(List<Date1> date1s){
        mDate1 = date1s;
        notifyDataSetChanged();
        Log.i("Curent4","Заход в дату1");
    }
    void setDate2(List<Date2> date2s){
        mDate2 = date2s;
        notifyDataSetChanged();
        Log.i("Curent5","Заход в дату2");
    }
    void setRating(List<Rating> ratings){
        mRating = ratings;
        notifyDataSetChanged();
        Log.i("Curent6","Заход в рейтинг");
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size()-1;
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView date1ItemView;
        private final TextView date2ItemView;
        private final RatingBar ratingItemView;
        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            date1ItemView = itemView.findViewById(R.id.Date1);
            date2ItemView = itemView.findViewById(R.id.Date2);
            ratingItemView=itemView.findViewById(R.id.ratingBar);
        }
    }
}
