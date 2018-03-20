package com.jw.booktracker;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.jw.booktracker.data.model.Book;
import com.jw.booktracker.data.persistence.DbHelper;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class AddActivity extends Activity {

    private DbHelper dbHelper;

    private EditText title;
    private EditText author;
    private EditText note;
    private RatingBar ratingBar;

    private Button addImageButton;

    private ImageView imageView;

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        book = (Book) getIntent().getSerializableExtra("tempBook");

        dbHelper = new DbHelper(this.getBaseContext());

        configureForm();
        configureBackButton();
        configureSaveButton();
        configuraAddImageButton();
    }

    private void configuraAddImageButton() {

        addImageButton = findViewById(R.id.addimagebtn);
        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Book tempBook = new Book(
                        null,
                        title.getText().toString(),
                        author.getText().toString(),
                        note.getText().toString(),
                        null,
                        Math.round(ratingBar.getRating())
                );

                Intent intent = new Intent(AddActivity.this, AddImageActivity.class);
                intent.putExtra("tempBook", tempBook);

                startActivity(intent);

            }
        });

    }


    private void configureForm() {
        title = findViewById(R.id.booktitle);
        author = findViewById(R.id.author);
        note = findViewById(R.id.note);
        ratingBar = findViewById(R.id.rating);

        imageView = findViewById(R.id.book_image_preview);

        if(book != null) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            note.setText(book.getNote());
            title.setText(book.getTitle());
            ratingBar.setRating(book.getRating());

            if (book.getImageUrl() != null && book.getImageUrl().length() > 0) {
                Picasso.get().load(book.getImageUrl()).into(imageView);


        }
    }}

    private void configureSaveButton() {
        Button button = findViewById(R.id.saveDbButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String imageUrl = "";

               if (book != null) {
                   imageUrl = book.getImageUrl();
               }

                dbHelper.getWritableDatabase();
                dbHelper.add(
                        new Book(
                                null,
                                title.getText().toString(),
                                author.getText().toString(),
                                note.getText().toString(),
                                imageUrl,
                                Math.round(ratingBar.getRating())
                        )
                );

            }
        });
    }


    private void configureBackButton() {
        Button btnBack = (Button) findViewById(R.id.backbtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity( new Intent(AddActivity.this, MainActivity.class));

            }
        });



    }


}

