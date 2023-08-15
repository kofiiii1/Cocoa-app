package com.example.cocofa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_POSTS_TABLE = ("CREATE TABLE " + TABLE_POSTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IMAGE + " BLOB,"
                + COLUMN_DESCRIPTION + " TEXT" + ")")
        db.execSQL(CREATE_POSTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS)

        // Create tables again
        onCreate(db)
    }

    fun addPost(post: Post) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_IMAGE, post.image)
        values.put(COLUMN_DESCRIPTION, post.description)

        db.insert(TABLE_POSTS, null, values)
        db.close()
    }

    fun getImageBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }


    fun getAllPosts(): ArrayList<Post> {
        val posts = ArrayList<Post>()
        val selectQuery = "SELECT  * FROM $TABLE_POSTS ORDER BY $COLUMN_ID DESC"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val post = Post(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    image = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                )
                posts.add(post)
            } while (cursor.moveToNext())
        }


        cursor.close()
        db.close()
        return posts
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "CocofaDB"

        private const val TABLE_POSTS = "posts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_DESCRIPTION = "description"
    }

}

