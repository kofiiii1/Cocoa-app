package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var info2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        val dbHelper = DBHelper(this)


        val loginBtn: Button = findViewById(R.id.login_btn)
        loginBtn.setOnClickListener {
            val db = dbHelper.readableDatabase
            val selection = "${DBHelper.COLUMN_USERNAME} = ? AND ${DBHelper.COLUMN_PASSWORD} = ?"
            val selectionArgs = arrayOf(username.text.toString(), password.text.toString())
            val cursor = db.query(
                DBHelper.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            // Check if the query returned a row
            if (cursor.moveToFirst()) {
                val intent = Intent(this@Login, MarketPriceActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Login Successful", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Incorrect Credentials", Toast.LENGTH_LONG).show()
                password.setText("")
            }

            // Close the cursor and database
            cursor.close()
            db.close()
        }

        info2 = findViewById(R.id.info2)

        info2.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Start a new intent to navigate to the specific activity
        val intent = Intent(this, Intro::class.java)
        startActivity(intent)
        // Close the current activity
        finish()
    }

}
