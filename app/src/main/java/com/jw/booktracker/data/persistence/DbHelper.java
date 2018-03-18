package com.jw.booktracker.data.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jw.booktracker.data.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "books";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "books_db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CREATE_TABLE);

        //TODO: TEST INSERT

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        // Create tables again
        onCreate(db);
    }

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title TEXT,"
                    + "author TEXT,"
                    + "rating INTEGER"
                    + ")";


    public Book getById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cur = db.rawQuery("select * from books where id = ?", new String[]{String.valueOf(id)});
        Book book = new Book();
        if (cur.moveToNext()) {

            book.setId(String.valueOf(cur.getInt(0)));
            book.setTitle(cur.getString(1));
            book.setAuthor(cur.getString(2));
            book.setRating(cur.getInt(3));

            cur.close();
            db.close();
        }

        return book;
    }

    public List<Book> getAllBooks() {

        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null) {
            return null;
        }

        List<Book> books = new ArrayList<>();
        //ContentValues row = new ContentValues();
        Cursor cur = db.rawQuery("select * from books", null);
        if (cur.moveToFirst()) {

            do {
                Book book = new Book();

                book.setId(String.valueOf(cur.getInt(0)));
                book.setTitle(cur.getString(1));
                book.setAuthor(cur.getString(2));
                book.setRating(cur.getInt(3));
                books.add(book);
            } while (cur.moveToNext());

            cur.close();
            db.close();
        }

        return books;
    }

    public long add(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put("title", book.getTitle());
        row.put("author", book.getAuthor());
        row.put("rating", book.getRating());

        long id = db.insert("books", null, row);
        db.close();

        System.out.println(id);

        return id;
    }

}