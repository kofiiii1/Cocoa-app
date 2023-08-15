package com.example.cocofa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class Companies : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var viewmapbtn: Button
    private var compItems = listOf(
        CompItem(
            "Fedco",
            "Fedco is a cocoa company that is committed to sustainable farming practices, ensuring that their cocoa is of the highest quality and grown in an environmentally friendly manner.",
            R.drawable.fedco,
            "Accra",
        ),
        CompItem(
            "Golden Tree",
            "Golden Tree is a cocoa company that is passionate about supporting small-scale cocoa farmers and helping to improve their livelihoods. They are committed to producing high-quality cocoa that is sustainably grown.",
            R.drawable.golden,
            "Kumasi",
        ),
        CompItem(
            "Steed",
            "Steed is a cocoa company that is focused on innovation and using cutting-edge farming techniques to produce the highest quality cocoa. They are committed to sustainability and supporting local communities.",
            R.drawable.steed,
            "Tema",
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companies)


        val compItems = listOf(
            CompItem(
                "Fedco",
                "Fedco is a cocoa company that is committed to sustainable farming practices, ensuring that their cocoa is of the highest quality and grown in an environmentally friendly manner.\nRate: 2.34",
                R.drawable.fedco,
                "Accra",
            ),
            CompItem(
                "Golden Tree",
                "Golden Tree is a cocoa company that is passionate about supporting small-scale cocoa farmers and helping to improve their livelihoods. They are committed to producing high-quality cocoa that is sustainably grown.\nRate: 2.68",
                R.drawable.golden,
                "Kumasi",
            ),
            CompItem(
                "Steed",
                "Steed is a cocoa company that is focused on innovation and using cutting-edge farming techniques to produce the highest quality cocoa. They are committed to sustainability and supporting local communities.\nRate: 2.88",
                R.drawable.steed,
                "Tema",
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.company_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CompAdapter(compItems)
        recyclerView.adapter = adapter


        searchView = findViewById(R.id.search_bar)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform search
                val filteredList = compItems.filter { it.title.contains(query!!, ignoreCase = true) || it.location.contains(query!!, ignoreCase = true) }
                adapter.updateList(filteredList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Update search results as the user types
                return true
            }

        })
        viewmapbtn = findViewById(R.id.viewmap)
        viewmapbtn.setOnClickListener {
            val intent = Intent(this@Companies, MapActivity::class.java)
            startActivity(intent)
        }


    }

}

data class CompItem(val title: String, val description: String, val image: Int, val location: String)

class CompAdapter(private var compItems: List<CompItem>) :
    RecyclerView.Adapter<CompAdapter.CompViewHolder>() {

    class CompViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.comp_name)
        val descriptionTextView: TextView = view.findViewById(R.id.comp_dis)
        val locationTextView: TextView = view.findViewById(R.id.location)
        val imageView: ImageView = view.findViewById(R.id.comp_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.company_card_item, parent, false)
        return CompViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompViewHolder, position: Int) {
        val compItem = compItems[position]
        holder.titleTextView.text = compItem.title
        holder.descriptionTextView.text = compItem.description
        holder.locationTextView.text = compItem.location
        holder.imageView.setImageResource(compItem.image)
    }

    fun updateList(newList: List<CompItem>) {
        compItems = newList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return compItems.size
    }
}
