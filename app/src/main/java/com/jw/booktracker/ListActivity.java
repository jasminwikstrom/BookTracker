package com.jw.booktracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jw.booktracker.data.model.Book;
import com.jw.booktracker.data.persistence.DbHelper;

import java.util.ArrayList;
import java.util.List;


/** View for all books in DB */
public class ListActivity extends Activity {

    private ListView listView;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper = new DbHelper(this.getBaseContext());

        configureBackButton();


        listView = (ListView) findViewById(R.id.list);

        populateList();
    }


    /** populates list, getting all books from DB */
    private void populateList() {
        List<String> items = new ArrayList<>(); // ta bort?

        List<Book> allBooks = dbHelper.getAllBooks();

        /** BRA förklaring behövs!!!!!! */
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, allBooks);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Book itemValue = (Book) listView.getItemAtPosition(i);

                Bundle params = new Bundle();
                params.putInt("id", Integer.parseInt(itemValue.getId()));

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtras(params);

                startActivity(intent);
            }

        });
    }


    private void configureBackButton() {
        Button btnBack = (Button) findViewById(R.id.backbtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));

            }
        });


    }


}

