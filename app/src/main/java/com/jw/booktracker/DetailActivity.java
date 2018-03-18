package com.jw.booktracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jw.booktracker.data.model.Book;
import com.jw.booktracker.data.persistence.DbHelper;


public class DetailActivity extends Activity {

    private DbHelper dbHelper;

    private TextView title;
    private TextView author;
    private RatingBar rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DbHelper(this.getBaseContext());

        title = findViewById(R.id.detail_title);
        author = findViewById(R.id.detail_author);
        rating = findViewById(R.id.detail_rating);

        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("id");

        dbHelper.getReadableDatabase();
        Book byId = dbHelper.getById(value);

        title.setText(byId.getTitle());
        author.setText(byId.getAuthor());
        rating.setRating(byId.getRating());


    }
}
