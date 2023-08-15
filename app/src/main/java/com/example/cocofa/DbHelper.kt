package com.example.cocofa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Define your table name and column names here
        const val TABLE_NAME = "users"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_CONTACT = "contact"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MyDatabase"
        private const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_CONTACT TEXT, $COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create your table here
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Upgrade your table here if needed
    }

    class User(val id: Int, val name: String, val email: String, val contact: String, val username: String, val password: String)


    fun insertUserData(name: String, email: String, contact: String, username: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_CONTACT, contact)
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }


}
