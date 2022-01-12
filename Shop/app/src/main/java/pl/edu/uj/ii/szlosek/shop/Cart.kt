package pl.edu.uj.ii.szlosek.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val selectedProductName = findViewById<TextView>(R.id.selectedProductName)
        selectedProductName.text = intent.getStringExtra("nameOfProduct")?.replace(",", "")
    }
}