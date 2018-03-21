package com.jw.booktracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jw.booktracker.data.model.Book;


/** View for adding image to book-object*/
public class AddImageActivity extends Activity {

    private TextView title;
    private TextView author;
    private TextView note;
    private RatingBar ratingBar;

    private EditText imageUrl;
    private Button button;

    private Book book;

    /** Starts logic of activity & gets the temporary book */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        book = (Book) getIntent().getSerializableExtra("tempBook");

        configLayout();
        populateTextViews();
    }

    /** WSends the book-object back to addActivity */
    private void configLayout() {

        title = findViewById(R.id.image_title);
        author = findViewById(R.id.image_author);
        note = findViewById(R.id.image_note);
        ratingBar = findViewById(R.id.image_rating);
        imageUrl = findViewById(R.id.image_add_image_url);

        button = findViewById(R.id.image_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                book.setImageUrl(imageUrl.getText().toString());

                Intent intent = new Intent(AddImageActivity.this, AddActivity.class);
                intent.putExtra("tempBook", book);
                startActivity(intent);
            }
        });
    }

    private void populateTextViews() {

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        note.setText(book.getNote());
        ratingBar.setRating(book.getRating());
    }
}
