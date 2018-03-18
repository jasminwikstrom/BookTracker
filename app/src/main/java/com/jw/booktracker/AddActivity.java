package com.jw.booktracker;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.jw.booktracker.data.model.Book;
import com.jw.booktracker.data.persistence.DbHelper;

public class AddActivity extends Activity {

    private DbHelper dbHelper;

    private EditText title;
    private EditText author;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new DbHelper(this.getBaseContext());

        configureForm();
        configureBackButton();
        configureSaveButton();
    }

    private void configureForm() {
        title = findViewById(R.id.booktitle);
        author = findViewById(R.id.author);
        ratingBar = findViewById(R.id.rating);
    }

    private void configureSaveButton() {
        Button button = findViewById(R.id.saveDbButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.getWritableDatabase();
                dbHelper.add(new Book(null, title.getText().toString(), author.getText().toString(),Math.round(ratingBar.getRating())));

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

