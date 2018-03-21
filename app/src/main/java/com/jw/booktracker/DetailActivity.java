package com.jw.booktracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jw.booktracker.data.model.Book;
import com.jw.booktracker.data.persistence.DbHelper;
import com.squareup.picasso.Picasso;

/** View for a detailed view of certain book*/
public class DetailActivity extends Activity {




    private DbHelper dbHelper;

    private TextView title;
    private TextView author;
    private TextView note;
    private RatingBar rating;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DbHelper(this.getBaseContext());

        configureBackButton();

        title = findViewById(R.id.detail_title);
        author = findViewById(R.id.detail_author);
        note = findViewById(R.id.detail_note);
        rating = findViewById(R.id.detail_rating);

        imageView = findViewById(R.id.book_image);


        /** Förklaring, other values????*/
        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("id");

        dbHelper.getReadableDatabase();
        Book byId = dbHelper.getById(value);

        title.setText(byId.getTitle());
        author.setText(byId.getAuthor());
        note.setText(byId.getNote());
        rating.setRating(byId.getRating());

        if (byId.getImageUrl() != null && byId.getImageUrl().length() > 0) {
            Picasso.get().load(byId.getImageUrl()).into(imageView);
        }
    }


    private void configureBackButton() {
        Button detail_back = (Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(DetailActivity.this, ListActivity.class));

            }
        });

    }
}
