package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Newsfeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsfeed)

        val newsItems = listOf(
            NewsItem(
                "New Cocoa Farming Technique Increases Yields",
                "Farmers in Ghana are reporting increased yields after adopting a new cocoa farming technique that involves planting shade trees and using organic fertilizers.",
                "April 5, 2023"
            ),
            NewsItem(
                "Ivory Coast Cocoa Prices on the Rise",
                "Prices for cocoa from Ivory Coast have been steadily rising over the past month due to increased demand from chocolate manufacturers and a shortage of supply.",
                "April 4, 2023"
            ),
            NewsItem(
                "New Study Links Cocoa Consumption to Lower Blood Pressure",
                "A new study has found that consuming cocoa products may help lower blood pressure, potentially reducing the risk of heart disease and stroke.",
                "April 3, 2023"
            ),
            NewsItem(
                "Cocoa Farmers in West Africa Struggle with Climate Change",
                "Farmers in West Africa, who produce over two-thirds of the world's cocoa, are struggling to adapt to the effects of climate change, including unpredictable rainfall and increased pests and diseases.",
                "April 2, 2023"
            ),
            NewsItem(
                "New Cocoa Processing Plant to Open in Indonesia",
                "Indonesia's cocoa industry is set to receive a boost with the opening of a new cocoa processing plant in Jakarta, which will increase the country's capacity to produce cocoa powder and butter.",
                "April 1, 2023"
            ),
            NewsItem(
                "Ghana to Introduce Minimum Cocoa Price Guarantee",
                "The government of Ghana has announced plans to introduce a minimum cocoa price guarantee for farmers in an effort to address the issue of low prices and ensure fair compensation for growers.",
                "March 31, 2023"
            ),
            NewsItem(
                "Cocoa and Chocolate Exhibition Draws Crowds in London",
                "The annual Chocolate Week exhibition in London drew large crowds this year, with visitors sampling a wide range of chocolate products and learning about the history and production of cocoa and chocolate.",
                "March 30, 2023"
            ),
            NewsItem(
                "Mars Inc. Commits to 100% Sustainable Cocoa Sourcing",
                "Mars Inc., one of the world's largest chocolate manufacturers, has pledged to source 100% of its cocoa sustainably by 2025, in an effort to address concerns about deforestation and child labor in the cocoa industry.",
                "March 29, 2023"
            ),
            NewsItem(
                "Nestle Launches New Range of Premium Cocoa Products",
                "Nestle has announced the launch of a new range of premium cocoa products, including single-origin chocolate bars and cocoa powders, in response to growing consumer demand for high-quality and sustainable chocolate.",
                "March 28, 2023"
            ),
            NewsItem(
                "Cocoa Farmers in Ghana and Ivory Coast Receive Mobile Payments",
                "Cocoa farmers in Ghana and Ivory Coast are now able to receive mobile payments for their crops, thanks to a new partnership between mobile payment providers and cocoa buyers, which aims to increase efficiency and transparency in the supply chain.",
                "March 27, 2023"
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NewsAdapter(newsItems)
        recyclerView.adapter = adapter



    }
}

data class NewsItem(val title: String, val description: String, val date: String)

class NewsAdapter(private val newsItems: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.news_title)
        val dateTextView: TextView = view.findViewById(R.id.news_date)
        val descriptionTextView: TextView = view.findViewById(R.id.news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsItems[position]
        holder.titleTextView.text = newsItem.title
        holder.dateTextView.text = newsItem.date
        holder.descriptionTextView.text = newsItem.description
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }


}