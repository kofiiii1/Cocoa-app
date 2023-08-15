package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    private lateinit var info2: TextView
    private lateinit var mName: EditText
    private lateinit var mEmail: EditText
    private lateinit var mContact: EditText
    private lateinit var mUsername: EditText
    private lateinit var mPassword: EditText
    private lateinit var mRegisterButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        info2 = findViewById(R.id.info2)
        mName = findViewById(R.id.name)
        mEmail = findViewById(R.id.email)
        mContact = findViewById(R.id.contact)
        mUsername = findViewById(R.id.username)
        mPassword = findViewById(R.id.password)
        mRegisterButton = findViewById(R.id.register_btn)


        mRegisterButton.setOnClickListener {
            // Get input values
            val name = mName.text.toString().trim()
            val email = mEmail.text.toString().trim()
            val contact = mContact.text.toString().trim()
            val username = mUsername.text.toString().trim()
            val password = mPassword.text.toString().trim()

            // Validate input values
            if (name.isEmpty()) {
                mName.error = "Please enter your name"
                mName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                mEmail.error = "Please enter your email"
                mEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEmail.error = "Please enter a valid email"
                mEmail.requestFocus()
                return@setOnClickListener
            }

            if (contact.isEmpty()) {
                mContact.error = "Please enter your contact number"
                mContact.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                mUsername.error = "Please enter a username"
                mUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                mPassword.error = "Please enter a password"
                mPassword.requestFocus()
                return@setOnClickListener
            }

            // Save user data to database
            val dbHelper = DBHelper(this)
            val isInserted = dbHelper.insertUserData(name, email, contact, username, password)

            // Show appropriate message based on database insert status
            if (isInserted) {
                Toast.makeText(this, "Farmer registered successfully!", Toast.LENGTH_SHORT).show()
                // Navigate to login activity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Registration failed, please try again", Toast.LENGTH_SHORT).show()
            }
        }


        info2.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Start a new intent to navigate to the specific activity
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        // Close the current activity
        finish()
    }
}

