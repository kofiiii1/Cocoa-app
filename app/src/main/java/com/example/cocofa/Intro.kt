package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val farmerBtn: Button = findViewById(R.id.farmer_btn)
        farmerBtn.setOnClickListener {
            val intent = Intent(this@Intro, Login::class.java)
            startActivity(intent)
        }

        val buyerBtn: Button = findViewById(R.id.buyer_btn)
        buyerBtn.setOnClickListener {
            val intent = Intent(this@Intro, BuyerLogin::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}