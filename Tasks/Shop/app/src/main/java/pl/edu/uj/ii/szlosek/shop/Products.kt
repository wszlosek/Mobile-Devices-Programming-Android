package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class Products : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val listView = findViewById<ListView>(R.id.productsList)
        listView.adapter = Adapter(this)

    }

    fun fromProductsToAboutMe(view: android.view.View) {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }
}