package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
class MarketPriceActivity : AppCompatActivity() {

    private lateinit var weight: EditText
    private lateinit var quantity: EditText
    private lateinit var price: TextView
    private lateinit var rate: Spinner
    private lateinit var logout: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_price)

        weight = findViewById(R.id.weight)
        quantity = findViewById(R.id.quantity)
        price = findViewById(R.id.cal_price)
        rate = findViewById(R.id.rate_spinner)
        logout = findViewById(R.id.logout)

        // Setup the bottom navigation bar
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tips_menu_item -> {
                    // Launch Tips activity
                    val intent = Intent(this@MarketPriceActivity, TipsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.market_price_menu_item -> {
                    // Launch MarketPrice activity
                    val intent = Intent(this@MarketPriceActivity, MarketPriceActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.comp_menu_item -> {
                    // Launch Tips activity
                    val intent = Intent(this@MarketPriceActivity, Companies::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val calculateButton: Button = findViewById(R.id.cal_btn)
        calculateButton.setOnClickListener {
            // Get the weight and quantity values as doubles
            val weightValue = weight.text.toString().toDoubleOrNull()
            val quantityValue = quantity.text.toString().toDoubleOrNull()
            val rateSelectedItem = rate.selectedItem.toString()
            val rateValue = if (rateSelectedItem != "Rate") rateSelectedItem.toDouble() else 2.88

            // Calculate the price if both weight and quantity are valid numbers
            if (weightValue != null && quantityValue != null ) {
                val totalPrice = weightValue * quantityValue * rateValue
                price.text = "$" + String.format("%.2f", totalPrice)
                weight.setText("")
                quantity.setText("")
            } else {
                price.text = "" // Clear price display if either value is invalid
            }
        }

        val uploadButton: Button = findViewById(R.id.post_button)
        uploadButton.setOnClickListener {
            val intent = Intent(this@MarketPriceActivity, Upload::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener{
            val intent = Intent(this@MarketPriceActivity, Login::class.java)
            startActivity(intent)
        }


    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

}
