package com.example.cocofa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("myAppPreferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        val username = sharedPref.getString("username", null)
        val password = sharedPref.getString("password", null)

        if (isLoggedIn && !username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            // User is already logged in, so take them directly to the main activity
            val intent = Intent(this, MarketPriceActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Wait for the specified duration and then start the main activity
        Handler().postDelayed({
            val intent = Intent(this, Intro::class.java)
            startActivity(intent)
        }, SPLASH_TIME_OUT.toLong())
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}